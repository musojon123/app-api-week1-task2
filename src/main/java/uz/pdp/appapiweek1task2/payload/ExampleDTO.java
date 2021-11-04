package uz.pdp.appapiweek1task2.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExampleDTO {
    private String text;
    private Integer taskId;
}
