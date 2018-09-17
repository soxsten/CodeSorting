import java.util.*;
import java.util.regex.Pattern;

class Codes {

    private static final String PATTERN = "\\";

    List<String> sortToUp(Set<String> unsortedCodes) {

        getMissedUnitFor(unsortedCodes);

        List<String> sortedCodes = new ArrayList<>(unsortedCodes);
        Collections.sort(sortedCodes);

        return sortedCodes;
    }

    List<String> sortToDown(Set<String> unsortedCodes) {

        getMissedUnitFor(unsortedCodes);
        List<List<String>> splittedCodes = new ArrayList<>();

//        Разбиваем по листам
        for (String code : unsortedCodes) {
            String[] split = code.split(Pattern.quote(PATTERN));

            while (splittedCodes.size() < split.length) {
                splittedCodes.add(new ArrayList<>());
            }

            List<String> part = splittedCodes.get(split.length - 1);
            part.add(code);
        }

//        Сортируем в каждом листе
        for (List<String> list : splittedCodes) {
            Collections.sort(list);
            Collections.reverse(list);
        }

        List<String> reverseOrder = new ArrayList<>();
        if (splittedCodes.size() > 1) {
            List<String> firstList = splittedCodes.get(0);

            for (String unitFromFirstList : firstList) {
                reverseOrder.add(unitFromFirstList);

                for (int j = 1; j < splittedCodes.size(); j++) {
                    List<String> nextList = splittedCodes.get(j);

                    for (String unitFromNextList : nextList) {
                        String[] split = unitFromNextList.split(Pattern.quote(PATTERN));

                        if (unitFromFirstList.equals(split[0])) {
                            reverseOrder.add(unitFromNextList);
                        }
                    }
                }
            }
        }

        return reverseOrder;
    }

    private void getMissedUnitFor(Set<String> unsortedCodes) {
        Set<String> uniqueCodes = new HashSet<>();
        for (String code : unsortedCodes) {
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

        unsortedCodes.addAll(uniqueCodes);
    }
}
