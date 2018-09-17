import java.util.*;
import java.util.regex.Pattern;

class Codes {

    private static final String PATTERN = Pattern.quote("\\");
    private Set<String> codes = new HashSet<>();

    void addCodes(Set<String> codes) {
        this.codes.addAll(codes);
    }

    List<String> sortToUp() {

        Set<String> result = new HashSet<>(codes);
        Set<String> uniqueCodes = new HashSet<>();

        for (String code : codes) {
            String[] split = code.split(PATTERN);

            if (split.length == 1) {
                String firstPart = split[0];
                uniqueCodes.add(firstPart);

            } else {
                int size = split.length - 1;
                StringBuilder unitName = new StringBuilder();

                for (int i = 0; i < size; i++) {
                    unitName.append(split[i]);

                    if (i < size - 1) {
                        unitName.append("\\");
                    }
                }

                uniqueCodes.add(unitName.toString());
            }
        }

        result.addAll(uniqueCodes);

        List<String> sortedList = new ArrayList<>(result);
        Collections.sort(sortedList);

        return sortedList;
    }

    List<String> sortToDown() {
        List<String> codes = sortToUp();
        Collections.reverse(codes);

        List<String> result = new ArrayList<>();

        List<String> block = new ArrayList<>();
        for (String code : codes) {

            String[] split = code.split(PATTERN);
            if (split.length == 1) {
                block.add(code);
                break;

            } else {
                block.add(code);
            }
        }

        return codes;
    }
}
