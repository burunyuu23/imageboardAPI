import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {

    public static <T> List<T> reverseList(List<T> list) {
        return IntStream.range(0, list.size())
                .map(i -> (list.size() - 1 - i))    // IntStream
                .mapToObj(list::get)                // Stream<T>
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public String simplifyPath(String path) {

        AtomicInteger skipped = new AtomicInteger();
        String pathed = reverseList(Arrays.stream(path.split("/"))
                .filter(s -> !s.isBlank() && !s.equals("."))
                .toList())
                .stream()
                .reduce("/", (acc, elem) -> {
                    if (elem.equals(".."))
                        skipped.getAndIncrement();
                    else {
                        if (skipped.get() > 0)
                            skipped.getAndDecrement();
                        else
                            return "/" + elem + acc;
                    }
                    return acc;
                });
        if (pathed.length() > 1)
            pathed = pathed.substring(0, pathed.length() - 1);
        return pathed;
    }
}