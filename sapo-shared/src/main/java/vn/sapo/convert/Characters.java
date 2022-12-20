package vn.sapo.convert;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.Random;
import java.util.regex.Pattern;

@Service
public class Characters {
    public String covertToString(String value) {
        try {
            String temp = Normalizer.normalize(value, Normalizer.Form.NFD);
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            Random generator = new Random();
            int ramdom = generator.nextInt(100) + 1;

            return (pattern.matcher(temp).replaceAll("").toUpperCase().replaceAll(" ", "")) + ramdom;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
