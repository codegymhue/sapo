package vn.sapo.customer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Accessors(chain = true)
public class CustomerParamExport {
    private String type;
    private List<Integer> listCustomerId;

    @Override
    public String toString() {
        return "CustomerParamExport{" +
                "type='" + type + '\'' +
                ", listCustomerId=" + listCustomerId +
                '}';
    }
}