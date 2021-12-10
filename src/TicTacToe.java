import java.util.*;

public class TicTacToe {
    static ArrayList<Integer> playerPlaces = new ArrayList<Integer>();
    static ArrayList<Integer> compPlaces = new ArrayList<Integer>();

    public static void main(String[] args) {

        char[][] gameBoard = {
                {' ', '|', ' ', '|', ' '},
                {'—', '+', '—', '+', '—'},
                {' ', '|', ' ', '|', ' '},
                {'—', '+', '—', '+', '—'},
                {' ', '|', ' ', '|', ' '}
        };

        printGameBoard(gameBoard);

        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Введите расположение символа(1-9)");
            int playerPlace = sc.nextInt();
            while (playerPlaces.contains(playerPlace) ||
                    compPlaces.contains(playerPlace)){
                System.out.println("Вы ввели неправильную позицию. Повторите ввод.");
                playerPlace = sc.nextInt();
            }

            placeSymbol(gameBoard, playerPlace, "player");
            String res = checkWin();
            if (res.length() > 0) {
                System.out.println(res);
                break;
            }

            Random rnd = new Random();
            int compPlace = rnd.nextInt(9) + 1;
            while (playerPlaces.contains(compPlace) ||
                    compPlaces.contains(compPlace)) {
                compPlace = rnd.nextInt(9) + 1;
            }

            placeSymbol(gameBoard, compPlace, "comp");

            printGameBoard(gameBoard);

            res = checkWin();
            if (res.length() > 0) {
                System.out.println(res);
                break;
            }
        }

    }

    public static void printGameBoard(char [] [] gameBoard){
        for (char [] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placeSymbol(char [] [] gameBoard, int place, String user){
        char sb = ' ';
        if (user.equals("player")){
            sb = 'X';
            playerPlaces.add(place);
        }else if (user.equals("comp")){
            sb = '0';
            compPlaces.add(place);
        }

        switch (place){
            case 1:
                gameBoard [0] [0] = sb;
                break;
            case 2:
                gameBoard [0] [2] = sb;
                break;
            case 3:
                gameBoard [0] [4] = sb;
                break;
            case 4:
                gameBoard [2] [0] = sb;
                break;
            case 5:
                gameBoard [2] [2] = sb;
                break;
            case 6:
                gameBoard [2] [4] = sb;
                break;
            case 7:
                gameBoard [4] [0] = sb;
                break;
            case 8:
                gameBoard [4] [2] = sb;
                break;
            case 9:
                gameBoard [4] [4] = sb;
                break;
            default:
                System.out.println("Введите расположение символа (1-9)");

        }
    }

    public static String checkWin() {
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(3, 5, 7);

        List<List> win = new ArrayList<List>();
        win.add(topRow);
        win.add(midRow);
        win.add(botRow);
        win.add(leftCol);
        win.add(midCol);
        win.add(rightCol);
        win.add(cross1);
        win.add(cross2);

        for (List l : win) {
            if (playerPlaces.containsAll(l)) {
                return "Вы выиграли!!!";
            } else if (compPlaces.containsAll(l)) {
                return "Вы проиграли";
            } else if ((playerPlaces.size() + compPlaces.size()) == 9) {
                return "Ничья";
            }
        }
        return "";
    }
}
