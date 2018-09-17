import java.util.*;
import java.util.regex.Pattern;

class Codes {

    private static final String PATTERN = "\\";
    private Set<String> codes = new HashSet<>();

    void addCodes(Set<String> codes) {
        this.codes.addAll(codes);
    }

    List<String> sortToUp() {

        Set<String> result = new HashSet<>(codes);
        Set<String> uniqueCodes = new HashSet<>();

        getMissedUnit(uniqueCodes);
        result.addAll(uniqueCodes);

        List<String> sortedList = new ArrayList<>(result);
        Collections.sort(sortedList);

        return sortedList;
    }

    List<String> sortToDown() {

        Set<String> result = new HashSet<>(codes);
        Set<String> uniqueCodes = new HashSet<>();

        getMissedUnit(uniqueCodes);
        result.addAll(uniqueCodes);

        List<String> sortedList = new ArrayList<>(result);
        Collections.sort(sortedList);
        Collections.reverse(sortedList);

        List<String> reversedList = new ArrayList<>();
        List<String> block = new LinkedList<>();
        for (int i = 1; i < sortedList.size() - 1; i++) {
            String currentUnit = sortedList.get(i);
            String[] partsOfCurrentUnit = currentUnit.split(Pattern.quote(PATTERN));

            String previousUnit = sortedList.get(i - 1);
            String[] partsOfPreviousUnit = previousUnit.split(Pattern.quote(PATTERN));

            if (partsOfCurrentUnit.length == partsOfPreviousUnit.length) {
                block.add(previousUnit);

                if (i == sortedList.size() - 2) {
                    block.add(currentUnit);
                    Collections.reverse(block);
                    reversedList.addAll(block);
                }

            } else {
                if (!block.isEmpty()) {
                    addReversedBlock(reversedList, block);
                }

                block.add(previousUnit);
                addReversedBlock(reversedList, block);

                if (i == sortedList.size() - 2) {
                    reversedList.add(previousUnit);
                    reversedList.add(currentUnit);
                }
            }
        }

        return reversedList;
    }

    private void getMissedUnit(Set<String> uniqueCodes) {
        for (String code : codes) {
            String[] split = code.split(Pattern.quote(PATTERN));

            if (split.length == 1) {
                String firstPart = split[0];
                uniqueCodes.add(firstPart);

            } else {
                int size = split.length - 1;
                StringBuilder unitName = new StringBuilder();

                for (int i = 0; i < size; i++) {
                    unitName.append(split[i]);

                    if (i < size - 1) {
                        unitName.append(PATTERN);
                    }
                }

                uniqueCodes.add(unitName.toString());
            }
        }
    }

    private void addReversedBlock(List<String> reversedList, List<String> block) {
        Collections.reverse(block);
        reversedList.addAll(block);
        block.clear();
    }
}
