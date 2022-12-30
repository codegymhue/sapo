package vn.sapo.shared.convert;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.Random;
import java.util.regex.Pattern;

@Service
public class Characters {
    public String covertToSKU(String value, int idProduct) {
        try {
            String temp = Normalizer.normalize(value, Normalizer.Form.NFD);
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            return (pattern.matcher(temp).replaceAll("").toUpperCase().replaceAll(" ", "")) + idProduct;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
