package graph.critical_path;

import java.util.Scanner;

public class critical_path {


    private static double find_max(double data1, double data2) {
        return data1 > data2 ? data1 : data2;
    }


    private static double find_min(double data1, double data2) {
        return data1 > data2 ? data2 : data1;
    }


    private static double[] Cal_ve(double B[][], int print_stack[]) {

        int num = print_stack.length - 1;
        double[] find_ve = new double[num + 1];
        boolean visited[] = new boolean[num + 1];

        for (int k = 0; k < num + 1; k++) {
            visited[k] = false;
        }

        for (int k = 0; k < num + 1; k++) {
            find_ve[k] = 0;
        }

        for (int k = 1; k < num + 1; k++) {
            int temp = print_stack[k];
            visited[temp] = true;
            for (int kk = 1; kk < num + 1; kk++) {
                if ((visited[kk] == true) && (B[kk][temp] != 0)) {
                    find_ve[temp] = find_max(find_ve[temp], find_ve[kk] + B[kk][temp]);
                }
            }
        }

        return find_ve;
    }


    private static double[] Cal_vl(double B[][], int print_stack[], double param) {
        int num = print_stack.length - 1;
        double[] find_vl = new double[num + 1];
        boolean visited[] = new boolean[num + 1];
        int ver_topology[] = new int[num + 1];


        ver_topology[0] = 0;
        for (int k = 1; k < num + 1; k++) {
            ver_topology[k] = print_stack[num + 1 - k];
        }


        for (int k = 0; k < num + 1; k++) {
            visited[k] = false;
        }

        for (int k = 0; k < num + 1; k++) {
            
            find_vl[k] = 10000;
        }
        find_vl[ver_topology[1]] = param;

        for (int k = 1; k < num + 1; k++) {
            int temp = ver_topology[k];
            visited[temp] = true;
            for (int kk = 1; kk < num + 1; kk++) {
                if ((visited[kk] == true) && (B[temp][kk] != 0)) {
                    find_vl[temp] = find_min(find_vl[temp], find_vl[kk] - B[temp][kk]);
                }
            }
        }

        return find_vl;
    }


    public static void main(String[] args) {
        topology_sort ts = new topology_sort();
        System.out.println("Input number of the nodes:");
        Scanner scan = new Scanner(System.in);
        int node_num = scan.nextInt();
        int A[][] = new int[node_num + 1][node_num + 1];
        double B[][] = new double[node_num + 1][node_num + 1];
        double ve[] = new double[node_num + 1];
        double vl[] = new double[node_num + 1];
        MyArrayStack<Integer> ms = new MyArrayStack<Integer>();


        for (int k = 0; k < node_num + 1; k++) {
            for (int k1 = 0; k1 < node_num + 1; k1++) {
                A[k][k1] = 0;
            }
        }
        System.out.println("Input adjacent nodes, -1 to finish your input:");
        int i = 0;
        int j = 0;
        while ((i = scan.nextInt()) != -1) {
            j = scan.nextInt();
            A[i][j] = 1;
            B[i][j] = scan.nextDouble();
        }
        MyArrayStack<Integer> mas = new MyArrayStack<Integer>();
        int print_stack[] = new int[node_num + 1];


        if (topology_sort.TopologyicalSort(A, mas, print_stack)) {
            System.out.println("the result of the topological sorting is:");
            for (int k = 1; k <= node_num; k++) {
                System.out.print(print_stack[k] + "  ");
            }
            System.out.println();
        } else {
            System.out.println("topological sorting failure, it means that the graph presented by this adjacent matrix has loop!");
            System.exit(0);
        }

        ve = Cal_ve(B, print_stack);
        double param = ve[print_stack[node_num]];
        vl = Cal_vl(B, print_stack, param);
//        ms.push(print_stack[1]);
        for (int k = 1; k < node_num + 1; k++) {
            int temp = print_stack[k];
            if (((ve[temp] - vl[temp]) == 0)) {
//                if (B[print_stack[ms.getTop()]][temp] != 0) {
                    ms.push(temp);
//                }
            }
        }

        int result[] = new int[ms.get_length() + 1];
        int k1 = 0;
        while (!ms.isEmpty()) {
            k1++;
            result[k1] = ms.pop();
        }

        for (int k = result.length - 1; k > 0; k--) {
            System.out.print(result[k] + " ");
        }
        System.out.println();


    }

}
