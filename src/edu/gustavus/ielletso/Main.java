package edu.gustavus.ielletso;

import java.util.Scanner;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Vector<Vector<String>> graphsVector = new Vector<Vector<String>>();
        while (true) {
            int graphSize = Integer.parseInt(scanner.nextLine());
            if (graphSize == -1) break;
            Vector<String> nGraphs = new Vector<String>();
            for (int i = 0; i < graphSize; ++i) {
                nGraphs.add(scanner.nextLine());
            }
            graphsVector.add(nGraphs);
        }
        scanner.close();
        int caseNum = 1;
        for (Vector<String> graphVector : graphsVector) {
            DAG graph = new DAG();
            for (String graphString : graphVector) {
                int[] xyz = new int[3];
                String[] strings = graphString.split(" ");
                for (int i = 0; i < 3; ++i) {
                    xyz[i] = Integer.parseInt(strings[i]);
                }
                graph.addNode(xyz[0], xyz[1], xyz[2]);
            }
            System.out.printf("Case %d: %d boxes\n\n", caseNum++, graph.longestPath());
        }
    }
}
