package az.duo.Night.Cinema.util;

import az.duo.Night.Cinema.enums.Genre;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Converter
@Component
public class GenreListConverter implements AttributeConverter<List<Genre>, String> {

    @Override
    public String convertToDatabaseColumn(List<Genre> attribute) {
        if (attribute == null || attribute.isEmpty()) return "";
        return attribute.stream()
                .map(Enum::name)
                .collect(Collectors.joining(","));
    }

    @Override
    public List<Genre> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isBlank()) return Collections.emptyList();

        return Arrays.stream(dbData.split(","))
                .map(String::trim)
                .map(Genre::valueOf)
                .collect(Collectors.toList());
    }
}
