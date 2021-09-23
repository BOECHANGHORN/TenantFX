package Utils;

import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;

import java.util.function.UnaryOperator;

public class DoubleFormatter {
    private TextFormatter<Double> textFormatter;

    public DoubleFormatter() {
        UnaryOperator<TextFormatter.Change> doubleNumValidationFormatter = change -> {
            if (change.getControlNewText().matches("\\d+\\.\\d+")) {
                return change;
            } else {
               return null;
            }
        };
        StringConverter<Double> converter = new StringConverter<>() {

            @Override
            public Double fromString(String s) {
                if (s.isEmpty() || "-".equals(s) || ".".equals(s) || "-.".equals(s)) {
                    return 0.0;
                } else {
                    return Double.valueOf(s);
                }
            }


            @Override
            public String toString(Double d) {
                return d.toString();
            }
        };

        this.textFormatter = new TextFormatter<>(converter, 0.0, doubleNumValidationFormatter);
    }

    public TextFormatter<Double> getInstance() {

        return this.textFormatter;
    }
}
