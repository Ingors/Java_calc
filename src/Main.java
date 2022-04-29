import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        String[] arrayOfElements = splitInput(input);
        // проверка введённых чисел на одинаковость стиля (оба должны быть арабскими или римскими)
        if (isNumberRoman(arrayOfElements[0]) != isNumberRoman(arrayOfElements[2])) {
            throw new Exception();
        }
        boolean isStyleRoman = isNumberRoman(arrayOfElements[0]);
        if (isStyleRoman) {
            System.out.println(romanCalc(arrayOfElements));
        }
        else {
            System.out.println(arabicCalc(arrayOfElements));
        }
    }
    public static String[] splitInput(String input) throws Exception {
        String[] m = input.split(" ");

        //проверка количества операндов (должно быть два) => должно быть 2 операнда + 1 знак оператора = 3 элемента массива
        if (m.length != 3) {
            throw new Exception();
        }

        //проверка операндов на дробность
        if (m[0].contains(".") || m[0].contains(",") || m[2].contains(".") || m[2].contains(",")) {
                throw new Exception();
            }
            return m;
        }

        public static boolean isNumberRoman(String number) {
            String[] romanNumbers  = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
            List<String> romanNumbersList = new ArrayList<>(Arrays.asList(romanNumbers));

            return romanNumbersList.contains(number);
        }
        public static Byte arabicCalc(String[] input) throws Exception {
            // проверка, входят ли числа в выражении в разрешёный интервал 1..10
            if (Byte.valueOf(input[0]) > 10||Byte.valueOf(input[2]) > 10||Byte.valueOf(input[0]) < 1||Byte.valueOf(input[2]) < 1) {
                throw new Exception();
            }
            byte result = 0;
            switch (input[1]) {
                case  ("+"):
                    result = (byte) (Byte.valueOf(input[0]) + Byte.valueOf(input[2]));
                    break;
                case ("-"):
                    result = (byte) (Byte.valueOf(input[0]) - Byte.valueOf(input[2]));
                    break;
                case ("*"):
                    result = (byte) (Byte.valueOf(input[0]) * Byte.valueOf(input[2]));
                    break;
                case ("/"):
                    result = (byte) (Byte.valueOf(input[0]) / Byte.valueOf(input[2]));
                    break;
                default:
                    throw new Exception();

            }
            return result;
        }
        public static String romanCalc(String[] input) throws Exception {
            //преобразование римских чисел в арабские
            String[] romanNumbers  = {"for_zero_index", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
            input[0] = String.valueOf(Arrays.asList(romanNumbers).indexOf(input[0]));
            input[2] = String.valueOf(Arrays.asList(romanNumbers).indexOf(input[2]));

            return IntegerConverter.intToRoman(arabicCalc(input));
        }
    }
class IntegerConverter {

    public static String intToRoman(int number) throws Exception {
        //проверка, не получился ли отрицательный результат, либо результат, равный 0 при введённых римских числах
        if (number <= 0) {
            throw new Exception();
        }
        //    return null;
        StringBuilder result = new StringBuilder();
        for(Integer key : units.descendingKeySet()) {
            while (number >= key) {
                number -= key;
                result.append(units.get(key));
            }
        }
        return result.toString();
    }

    private static final NavigableMap<Integer, String> units;
    static {
        NavigableMap<Integer, String> initMap = new TreeMap<>();
        initMap.put(1000, "M");
        initMap.put(900, "CM");
        initMap.put(500, "D");
        initMap.put(400, "CD");
        initMap.put(100, "C");
        initMap.put(90, "XC");
        initMap.put(50, "L");
        initMap.put(40, "XL");
        initMap.put(10, "X");
        initMap.put(9, "IX");
        initMap.put(5, "V");
        initMap.put(4, "IV");
        initMap.put(1, "I");
        units = Collections.unmodifiableNavigableMap(initMap);
    }
}