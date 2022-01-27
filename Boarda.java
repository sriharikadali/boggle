
/**
 * 
 */
/**
 * @author srihari.kadali
 *
 */
import java.util.*;
import java.io.*;
import java.util.Scanner;

public class Boarda {
    private char board[][];
    private int boardSize;

    public Boarda(int boardSize) {
        if (boardSize < 4)
            throw new RuntimeException("Board size must be 4 or greater.");
        this.boardSize = boardSize;
        board = new char[this.boardSize][this.boardSize];
    }

    public void createBoard() {

        try {
            List<String> listOfStrings = new ArrayList<String>();
            BufferedReader bf = new BufferedReader(
                    new FileReader("test.txt"));
            String line = bf.readLine();
            while (line != null) {
                listOfStrings.add(line);
                line = bf.readLine();
            }
            bf.close();
            String[] array = listOfStrings.toArray(new String[0]);

            for (int row = 0; row < this.boardSize; row++) {

                for (int col = 0, i = 0; col < this.boardSize; col++, i = i + 2) {
                    this.board[row][col] = array[row].charAt(i);
                }
            }

        } catch (IOException e) {
            System.out.println("exception caught" + e);
        }
    }

    public void printBoard() {
        for (int row = 0; row < this.boardSize; row++) {
            for (int col = 0; col < this.boardSize; col++) {
                System.out.print("  " + this.board[row][col]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        String dictionary[] = { "barbecue", "forceable", "brucella", "freezeout", "enforceable" };
        TrieNode root = new TrieNode();
        int n = dictionary.length;
        Scanner sc = new Scanner(System.in);
        System.out.println("please enter the size of the board");
        int size = sc.nextInt();
        Board b = new Board(size);
        arryboard=b.createBoard();
        b.printBoard();
        for (int i = 0; i < n; i++)
            insert(root, dictionary[i]);
        findWords(arrayboard, root);
    }

    static class TrieNode {
        TrieNode[] Child = new TrieNode[SIZE];

        boolean leaf;

        public TrieNode() {
            leaf = false;
            for (int i = 0; i < SIZE; i++)
                Child[i] = null;
        }
    }

    static void insert(TrieNode root, String Key) {
        int n = Key.length();
        TrieNode pChild = root;

        for (int i = 0; i < n; i++) {
            int index = Key.charAt(i) - 'A';

            if (pChild.Child[index] == null)
                pChild.Child[index] = new TrieNode();

            pChild = pChild.Child[index];
        }

        pChild.leaf = true;
    }

    static boolean isSafe(int i, int j, boolean visited[][]) {
        return (i >= 0 && i < M && j >= 0
                && j < N && !visited[i][j]);
    }

    static void searchWord(TrieNode root, char boggle[][], int i,
            int j, boolean visited[][], String str) {
        if (root.leaf == true)
            System.out.println(str);

        if (isSafe(i, j, visited)) {
            visited[i][j] = true;

            for (int K = 0; K < SIZE; K++) {
                if (root.Child[K] != null) {
                    char ch = (char) (K + 'A');

                    if (isSafe(i + 1, j + 1, visited)
                            && boggle[i + 1][j + 1] == ch)
                        searchWord(root.Child[K], boggle,
                                i + 1, j + 1,
                                visited, str + ch);
                    if (isSafe(i, j + 1, visited)
                            && boggle[i][j + 1] == ch)
                        searchWord(root.Child[K], boggle,
                                i, j + 1,
                                visited, str + ch);
                    if (isSafe(i - 1, j + 1, visited)
                            && boggle[i - 1][j + 1] == ch)
                        searchWord(root.Child[K], boggle,
                                i - 1, j + 1,
                                visited, str + ch);
                    if (isSafe(i + 1, j, visited)
                            && boggle[i + 1][j] == ch)
                        searchWord(root.Child[K], boggle,
                                i + 1, j,
                                visited, str + ch);
                    if (isSafe(i + 1, j - 1, visited)
                            && boggle[i + 1][j - 1] == ch)
                        searchWord(root.Child[K], boggle,
                                i + 1, j - 1,
                                visited, str + ch);
                    if (isSafe(i, j - 1, visited)
                            && boggle[i][j - 1] == ch)
                        searchWord(root.Child[K], boggle,
                                i, j - 1,
                                visited, str + ch);
                    if (isSafe(i - 1, j - 1, visited)
                            && boggle[i - 1][j - 1] == ch)
                        searchWord(root.Child[K], boggle,
                                i - 1, j - 1,
                                visited, str + ch);
                    if (isSafe(i - 1, j, visited)
                            && boggle[i - 1][j] == ch)
                        searchWord(root.Child[K], boggle,
                                i - 1, j,
                                visited, str + ch);
                }
            }

            visited[i][j] = false;
        }
    }

    static void findWords(char boggle[][], TrieNode root) {
        boolean[][] visited = new boolean[M][N];
        TrieNode pChild = root;
        String str = "";
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (pChild.Child[(boggle[i][j]) - 'A'] != null) {
                    str = str + boggle[i][j];
                    searchWord(pChild.Child[(boggle[i][j]) - 'A'],
                            boggle, i, j, visited, str);
                    str = "";
                }
            }
        }
    }
}
