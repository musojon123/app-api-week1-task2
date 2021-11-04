package uz.pdp.appapiweek1task2.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    private String name;
    private String text;
    private String solution;
    private String hint;
    private String method;
    private Boolean hasStar;
    private Integer languageId;
}
