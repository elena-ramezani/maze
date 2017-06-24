package com.company;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.*;
import java.util.*;




public class Main {
    //static int[][] matrix;
    static int col;
    static int row;
    int y=0;
    int x=0;


    public static void main(String[] args) { // initialized
        int x_start = 12;
        int y_start=18;
        int x_end = 16;
        int y_end = 40;
        boolean sol;

        List<List<Integer>> mazeMatrix = new ArrayList<List<Integer>>();  // making arraylist for matrix
        ArrayList<Maze> mazeList;
        mazeList = new ArrayList<Maze>();
        mazeMatrix = readMaze("../../../Maze - Input.csv");// mazeMatrix is the result of reading the maze
        mazeMatrix.get(x_end).set(y_end, 2); // initialized the end by two
        sol = check(mazeMatrix,x_start,y_start); // sol is the result if the recursive function
        System.out.println(sol);
        mazeMatrix.get(x_start).set(y_start, 5); // initialized start by 5
        drawpath(mazeMatrix); // draw maze function


    }


    public static List<List<Integer>> readMaze(String csvfile) { // because it is static the result could only be used inside that functon
        List<List<Integer>> matrix = new ArrayList<List<Integer>>();
        BufferedReader br = null;
        String line = "";
        String csvSplitBy = ",";
        int y = 0;


        try {  // read from the file

            br = new BufferedReader(new FileReader(csvfile));
            br.readLine(); // reading the first line becuase we dont need the first line
            while ((line = br.readLine()) != null) {

                String[] info = line.split(csvSplitBy);
                col = info.length;
                matrix.add(new ArrayList<Integer>()); // fisr we add first row to add the element to that row

                for (int x = 1; x < info.length; x++) {
                    matrix.get(y).add(Integer.parseInt(info[x])); // get from that row you read
                }
                y ++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return matrix;
    }

    public static boolean check(List<List<Integer>> mazeMatrix,int row,int col) { // recursive function
        int length_col = mazeMatrix.get(0).size();
        int length_row = mazeMatrix.size();

        if (mazeMatrix.get(row).get(col) == 2) {
            return true;
        } else if (mazeMatrix.get(row).get(col)==1){
            return false;
        } else if (mazeMatrix.get(row).get(col)==3){
            return false;
        }

        mazeMatrix.get(row).set(col,3);  // recursive

        if (   (col < length_col-1 && check(mazeMatrix, row,col+1))
           || (row < length_row-1 && check(mazeMatrix,row+1,col))
           || (col > 0        && check(mazeMatrix, row,col-1))
           || (row > 0        && check(mazeMatrix,row-1, col))){
            return true;
        }else {
           mazeMatrix.get(row).set(col,-1);
        }

        return false;

    }
    public static void drawpath(List<List<Integer>> mazeMatrix){

        for(int i=0;i<mazeMatrix.size();i++){
            for(int j=0;j<mazeMatrix.get(0).size();j++) {
                mazeMatrix.get(i).get(j);
                if (mazeMatrix.get(i).get(j) == 3) {
                    System.out.print("p ");                      // path
                } else if (mazeMatrix.get(i).get(j) == 1) {
                    System.out.print("| ");                      // wall
                } else if (mazeMatrix.get(i).get(j) == 2) {
                    System.out.print("End ");                      //end
                } else if (mazeMatrix.get(i).get(j)==-1){
                    System.out.print("v ");                     // visited
                } else if (mazeMatrix.get(i).get(j)== 5){
                    System.out.print("S ");                    //start
                }

                else System.out.print("0 ");
            }
            System.out.println(" ");
        }



    }




}
