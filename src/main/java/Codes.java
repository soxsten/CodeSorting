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
            list.sort(Comparator.reverseOrder());
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

            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < split.length; i++) {

                if (i != 0) {
                    builder.append(PATTERN);
                }

                builder.append(split[i]);
                uniqueCodes.add(builder.toString());
            }
        }

        unsortedCodes.addAll(uniqueCodes);
    }
}
