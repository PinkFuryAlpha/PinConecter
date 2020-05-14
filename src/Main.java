import java.io.File;
import java.io.IOException;
import java.util.*;
import java.lang.Object;

public class Main {

    public static int N=16;

    public static void main(String[] args) throws IOException

    {


        File file = new File("test.txt");
        Scanner myReader = new Scanner(file);

        //the board itself with the pins;
        int[][] board=new int[N][N];

        //here we store the pin values coordinates in th matrix, and the pin values;
        HashMap<Integer, ArrayList<Point>> indexes= new HashMap<>();

        //putting the values on the board;
        load(board,myReader);

        //the matrix in which we store the route
        int[][] visited=new int[N][N];
        fillZero(visited);

        System.out.println("\n");

        printMatrix(board);

        System.out.println("\n");

        mapLoad(indexes,board);


        for(Map.Entry<Integer, ArrayList<Point>> entry : indexes.entrySet()){
            for(int i=0;i<entry.getValue().size()-1;i++)
                drawSolution(board,visited,entry.getKey(),entry.getValue().get(i).x,entry.getValue().get(i).y,entry.getValue().get(i+1).x,entry.getValue().get(i+1).y);
        }

        printMatrix(board);




    }

    public static void load(int[][] board,Scanner myReader) {


        while (myReader.hasNextLine()) {
            for (int i = 0; i < board.length; i++) {
                String[] line = myReader.nextLine().trim().split(" ");
                for (int j = 0; j < line.length; j++) {
                    board[i][j] = Integer.parseInt(line[j]);
                }
            }
        }


    }

    public static void mapLoad(HashMap<Integer,ArrayList<Point>> indexes,int[][] board){
        for(int i=0;i<16;i++) {
            for (int j = 0; j < 16; j++) {
                if (board[i][j] != 0) {
                    Point p=new Point(i,j);
                    if(indexes.isEmpty() || !indexes.containsKey(board[i][j])){
                        ArrayList<Point> list= new ArrayList<>();
                        list.add(p);
                        indexes.put(board[i][j],list);
                    }else{
                        ArrayList<Point> list=indexes.get(board[i][j]);
                        list.add(p);
                        indexes.put(board[i][j],list);
                    }
                }
            }
        }

        // Functie de printare a map-ului
        for(Map.Entry<Integer, ArrayList<Point>> entry : indexes.entrySet()){
            System.out.println(entry.getKey()+" ");
            for(Point p: entry.getValue()){
                System.out.print("x:"+p.x+" y:"+p.y+" ");
            }
            System.out.println("\n");
        }

    }

    public static void fillZero(int[][] array){
        for (int[] row: array)
            Arrays.fill(row,0);


    }

    // A utility function to print matrix
    public static void printMatrix(int[][] sol)
    {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(" " + sol[i][j] + " ");
            System.out.println();
        }
    }


    static  int[] ROW={0,0 ,-1,1};
    static  int[] COL={1,-1, 0,0};

    //BFS cu backtrack
    public static void drawSolution(int[][] board,int[][] visited,int value,int startX,int startY,int endX,int endY){

        Queue<Point> q = new ArrayDeque<>();

        visited[startX][startY]=value;

        q.add(new Point(startX, startY, 0));

        int min_dist = Integer.MAX_VALUE;
        Point end=new Point();

        int initialX=startX;
        int initialY=startY;

        while(!q.isEmpty()) {
            Point point = q.poll();

            startX = point.x;
            startY = point.y;
            int dist = point.dist;

            if (startX == endX && startY == endY) {
                min_dist = dist;
                end = point;
                System.out.println(end.x + " " + end.y);
                //printMatrix(visited);
                fillZero(visited);
                break;
            }

            for (int k = 0; k < 4; k++) {
                if (isValid(board, visited, startX + ROW[k], startY + COL[k], value)) {
                    visited[startX + ROW[k]][startY + COL[k]] = value;
                    Point pin = new Point(startX + ROW[k], startY + COL[k], dist + 1);
                    pin.parent = point;
                    q.add(pin);
                }
            }


        }


        System.out.println("\n");

        //backtrack path
        while ((end.x!=initialX) || (end.y!=initialY)){
            board[end.x][end.y]=value;
            end= end.parent;
        }

        System.out.println("\n");

        if (min_dist != Integer.MAX_VALUE) {
            System.out.print("The shortest path from source to destination " +
                    "has length " + min_dist);
        }
        else {
            System.out.print("Destination can't be reached from given source");
        }
        System.out.println("\n");
    }

    //functie auxiliara ce verifica daca este posibila trecerea la urmatorul pin
    public static boolean isValid(int[][] board,int[][] visited, int x,int y,int value){
        if ((x>= 0) && (x< N) && (y>= 0) && (y< N) && (board[x][y]==0 || board[x][y]==value ) && (visited[x][y] == 0))
        {return true;}
        return false;
    }





}

