package uz.pdp.appapiweek1task2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appapiweek1task2.entity.ProgrammingLanguage;
import uz.pdp.appapiweek1task2.entity.Task;
import uz.pdp.appapiweek1task2.payload.ApiResponse;
import uz.pdp.appapiweek1task2.payload.TaskDTO;
import uz.pdp.appapiweek1task2.repository.ProgrammingLanguageRepository;
import uz.pdp.appapiweek1task2.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    ProgrammingLanguageRepository languageRepository;


    public List<Task> getTasks(){
        return taskRepository.findAll();
    }

    public Task getTask(Integer id){
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty())
            return null;
        return optionalTask.get();
    }

    public ApiResponse addTask(TaskDTO taskDTO){
        Optional<ProgrammingLanguage> optionalProgrammingLanguage = languageRepository.findById(taskDTO.getLanguageId());
        if (optionalProgrammingLanguage.isEmpty())
            return new ApiResponse("Yuq", false);

        Task task = new Task();
        task.setName(taskDTO.getName());
        task.setText(taskDTO.getText());
        task.setSolution(taskDTO.getSolution());
        task.setHint(taskDTO.getHint());
        task.setMethod(taskDTO.getMethod());
        task.setHasStar(taskDTO.getHasStar());
        task.setLanguage(optionalProgrammingLanguage.get());
        taskRepository.save(task);
        return new ApiResponse("Successfully added", true);
    }

    public ApiResponse editTask(TaskDTO taskDTO, Integer id){
        Optional<Task> optionalTask = taskRepository.findById(id);
        Optional<ProgrammingLanguage> optionalProgrammingLanguage = languageRepository.findById(taskDTO.getLanguageId());
        if (optionalTask.isEmpty())
            return new ApiResponse("Bunday task yuq", false );
        if (optionalProgrammingLanguage.isEmpty())
            return new ApiResponse("Bynday lang yuq", false);
        Task editingTask = optionalTask.get();
        editingTask.setName(taskDTO.getName());
        editingTask.setText(taskDTO.getText());
        editingTask.setSolution(taskDTO.getSolution());
        editingTask.setHint(taskDTO.getHint());
        editingTask.setMethod(taskDTO.getMethod());
        editingTask.setHasStar(taskDTO.getHasStar());
        editingTask.setLanguage(optionalProgrammingLanguage.get());
        taskRepository.save(editingTask);

        return new ApiResponse("Successfully edited",true);
    }

    public ApiResponse deleteTask(Integer id){
        if (!taskRepository.existsById(id))
            return new ApiResponse("Bunday answer mavjud amas", false);
        taskRepository.deleteById(id);
        return new ApiResponse("Successfully deleted", true );
    }
}
