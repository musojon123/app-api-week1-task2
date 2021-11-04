package uz.pdp.appapiweek1task2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appapiweek1task2.entity.Category;
import uz.pdp.appapiweek1task2.entity.Example;
import uz.pdp.appapiweek1task2.entity.ProgrammingLanguage;
import uz.pdp.appapiweek1task2.entity.Task;
import uz.pdp.appapiweek1task2.payload.ApiResponse;
import uz.pdp.appapiweek1task2.payload.CategoryDTO;
import uz.pdp.appapiweek1task2.payload.ExampleDTO;
import uz.pdp.appapiweek1task2.repository.CategoryRepository;
import uz.pdp.appapiweek1task2.repository.ExampleRepository;
import uz.pdp.appapiweek1task2.repository.ProgrammingLanguageRepository;
import uz.pdp.appapiweek1task2.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ExampleService {
    @Autowired
    ExampleRepository exampleRepository;
    @Autowired
    TaskRepository taskRepository;


    public List<Example> getExamples(){
        return exampleRepository.findAll();
    }

    public Example getExample(Integer id){
        Optional<Example> optionalExample = exampleRepository.findById(id);
        if (optionalExample.isEmpty())
            return null;
        return optionalExample.get();
    }

    public ApiResponse addExample(ExampleDTO exampleDTO){
        Optional<Task> optionalTask = taskRepository.findById(exampleDTO.getTaskId());
        if (optionalTask.isEmpty())
            return new ApiResponse("Bunday task yuq", false);

        Example example = new Example();
        example.setText(exampleDTO.getText());
        example.setTask(optionalTask.get());
        return new ApiResponse("Successfully added", true);
    }

    public ApiResponse editExample(ExampleDTO exampleDTO, Integer id){
        Optional<Example> optionalExample = exampleRepository.findById(id);
        Optional<Task> optionalTask = taskRepository.findById(exampleDTO.getTaskId());
        if (optionalTask.isEmpty())
            return new ApiResponse("Bunday task yuq", false );

        if (optionalExample.isEmpty())
            return new ApiResponse("Bunday Example mavjud emas", false);

        Example example = optionalExample.get();
        example.setText(exampleDTO.getText());
        example.setTask(optionalTask.get());
        exampleRepository.save(example);
        return new ApiResponse("Successfully edited",true);
    }

    public ApiResponse deleteExample(Integer id){
        if (!exampleRepository.existsById(id))
            return new ApiResponse("Bunday answer mavjud amas", false);
        exampleRepository.deleteById(id);
        return new ApiResponse("Successfully deleted", true );
    }
}
