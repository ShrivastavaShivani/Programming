package Hackerrank;

import java.lang.*;
import java.io.*;
import java.util.*;

public class bfsshortpath {
    public static void run() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        while(t-->0) {
            String[] nm = br.readLine().split(" ");
            int n = Integer.parseInt(nm[0]);
            int m = Integer.parseInt(nm[1]);
            List<List<Integer>> edges = new ArrayList<>();
            for (int i=0; i<m; i++) {
                String[] e = br.readLine().split(" ");
                int a = Integer.parseInt(e[0]);
                int b = Integer.parseInt(e[1]);
                edges.add(Arrays.asList(a,b));
            }
            int s = Integer.parseInt(br.readLine().trim());
            for (int a: bfs(n, m, edges, s)) sb.append(a).append(" ");
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
    public static List<Integer> bfs(int n, int m, List<List<Integer>> edges, int s) {
        // Write your code here
        //   boolean[] bool = new boolean[n];
        //   int[] res = new int[n];
        //   Queue<Integer> q = new LinkedList<>();
        //   int[] level = new int[n];
        //   level[s-1]=1;
        //   q.add(s);
        //   while(q.size()>0)
        //   {
        //       int cur = q.remove();
        //       if(bool[cur-1])
        //       continue;
        //       for(int i=0; i<edges.size();i++)
        //       {
        //           int a = edges.get(i).get(0);
        //           int b = edges.get(i).get(1);
        //           if(a == cur && !bool[b-1])
        //           {
        //               q.add(b);
        //               if(level[b-1]==0)
        //               level[b-1] = level[cur-1]+1;
        //           }
        //           if(b == cur && !bool[a-1])
        //           {
        //               q.add(a);
        //               if(level[a-1]==0)
        //               level[a-1] = level[cur-1]+1;
        //           }
        //       }
        //       bool[cur-1] = true;
        //   }
        //   List<Integer> ret = new ArrayList<>();
        //   for(int i=0; i<n; i++)
        //   {
        //       if(level[i]==0)
        //       res[i] = -1;
        //       else
        //       {
        //           res[i] = (level[i]-1)*6;
        //       }
        //       if(i+1!=s)
        //       {
        //           ret.add(res[i]);
        //       }
        //   }
        //   return ret;

        boolean[] visited = new boolean[n];
        int[] levels = new int[n];
        Arrays.fill(levels, -1);
        levels[s-1] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        while(q.size()>0) {
            int curr = q.remove();
            if (visited[curr-1]) continue;
            for(List<Integer> e: edges) {
                int a = e.get(0);
                int b = e.get(1);
                if (a == curr && !visited[b-1]) {
                    q.add(b);
                    if (levels[b-1] == -1)
                        levels[b-1] = levels[curr-1]+1;
                }
                if (b == curr && !visited[a-1]) {
                    q.add(a);
                    if (levels[a-1] == -1)
                        levels[a-1] = levels[curr-1]+1;
                }
            }
            visited[curr-1]=true;
        }
        List<Integer> result = new ArrayList<>();
        for (int i=0; i<n; i++) {
            if (i!=s-1) {
                if (levels[i]!=-1) levels[i]*=6;
                result.add(levels[i]);
            }
        }
        return result;
    }
}
