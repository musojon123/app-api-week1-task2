package uz.pdp.appapiweek1task2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appapiweek1task2.entity.Answer;
import uz.pdp.appapiweek1task2.entity.Task;
import uz.pdp.appapiweek1task2.entity.User;
import uz.pdp.appapiweek1task2.payload.AnswerDTO;
import uz.pdp.appapiweek1task2.payload.ApiResponse;
import uz.pdp.appapiweek1task2.repository.AnswerRepository;
import uz.pdp.appapiweek1task2.repository.TaskRepository;
import uz.pdp.appapiweek1task2.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TaskRepository taskRepository;

    public List<Answer> getAnswers(){
        return answerRepository.findAll();
    }

    public Answer getAnswer(Integer id){
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (optionalAnswer.isEmpty())
            return null;
        return optionalAnswer.get();
    }

    public ApiResponse addAnswer(AnswerDTO answerDTO){
        Optional<User> optionalUser = userRepository.findById(answerDTO.getUserId());
        Optional<Task> taskOptional = taskRepository.findById(answerDTO.getTaskId());
        if (optionalUser.isEmpty())
            return new ApiResponse("Bunday User mavjud emas", false);
        if (taskOptional.isEmpty())
            return new ApiResponse("Bunday task mavjud emas", false);
        Answer answer = new Answer();
        answer.setText(answerDTO.getText());
        answer.setIsCorrect(answerDTO.getIsCorrect());
        answer.setTask(taskOptional.get());
        answer.setUser(optionalUser.get());
        answerRepository.save(answer);
        return new ApiResponse("Successfully added", true);
    }

    public ApiResponse editAnswer(AnswerDTO answerDTO, Integer id){
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        Optional<User> optionalUser = userRepository.findById(answerDTO.getUserId());
        Optional<Task> taskOptional = taskRepository.findById(answerDTO.getTaskId());
        if (optionalAnswer.isEmpty())
            return new ApiResponse("Bunday answer mavjud emas", false);
        if (optionalUser.isEmpty())
            return new ApiResponse("Bunday user mavjud emas", false);
        if (taskOptional.isPresent())
            return new ApiResponse("Bunday task mavjud emas", false);

        Answer editingAnswer = optionalAnswer.get();
        editingAnswer.setText(answerDTO.getText());
        editingAnswer.setIsCorrect(answerDTO.getIsCorrect());
        editingAnswer.setTask(taskOptional.get());
        editingAnswer.setUser(optionalUser.get());
        answerRepository.save(editingAnswer);
        return new ApiResponse("Successfully edited",true);
    }

    public ApiResponse deleteAnswer(Integer id){
        if (!answerRepository.existsById(id))
            return new ApiResponse("Bunday answer mavjud amas", false);
        answerRepository.deleteById(id);
        return new ApiResponse("Successfully deleted", true );
    }
}
