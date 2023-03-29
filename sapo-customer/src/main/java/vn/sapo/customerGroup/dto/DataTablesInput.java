package vn.sapo.customerGroup.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DataTablesInput<T> {

    @NotNull
    @Min(0)
    private Integer draw = 1;

    @NotNull
    @Min(0)
    private Integer start = 0;

    @NotNull
    @Min(-1)
    private Integer length = 10;

    @NotEmpty
    private List<T> order = new ArrayList<>();

}
