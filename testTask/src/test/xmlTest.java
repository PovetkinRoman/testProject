import com.rpovetkin.testTask.model.Project;
import com.rpovetkin.testTask.model.Task;
import com.rpovetkin.testTask.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
public class xmlTest {

    private Project project;
    @Before
    public void setUp() {
        List<Task> tasks = new ArrayList<>();
        Task task = new Task("descriptionTest", "testName");
        tasks.add(task);
        Task user = new Task("User1", "1L");
        project = new Project("projectNameTest", "descriptionTest", tasks);
    }

    @After
    public void tearDown() {
        project = null;
    }

    @Test
    public void testObjectToXml() throws JAXBException, FileNotFoundException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Project.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(project, new File("project.xml"));
        marshaller.marshal(project, System.out);
    }



    @Test
    public void testXmlToObject() throws JAXBException, FileNotFoundException {

        Project projectOutput;
        File file = new File("src/main/resources/project.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(Project.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        projectOutput = (Project) unmarshaller.unmarshal(file);

        System.out.println(projectOutput);

    }
}
