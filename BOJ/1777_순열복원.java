import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N;
    static int[] arr, tree, ans;

    public static void main(String... args) throws IOException {
        N = io.nextInt();
        arr = new int[N + 1];
        tree = new int[1 << (int)Math.ceil(Math.log(N) / Math.log(2)) + 1];
        ans = new int[N + 1];
        StringBuilder res = new StringBuilder();

        for (int i = 1; i < N + 1; i++) {
            arr[i] = io.nextInt();
        }

        init(1, 1, N);

        for (int i = N; i > 0; i--) {
            int q = query(i - arr[i], 1, 1, N);
            ans[q] = i;
            update(q, 0, 1, 1, N);
        }

        for (int i = 1; i < N + 1; i++) {
            res.append(ans[i]).append(' ');
        }

        io.write(res);
    }

    private static int init(int n, int s, int e) {
        if (s == e)
            return tree[n] = 1;
        int m = s + e >>> 1;
        return tree[n] = init(n * 2, s, m) + init(n * 2 + 1, m + 1, e);
    }

    private static int update(int i, int v, int n, int s, int e) {
        if (i > e || i < s)
            return tree[n];
        if (s == e)
            return tree[n] = v;
        int m = s + e >>> 1;
        return tree[n] = update(i, v, n * 2, s, m) + update(i, v, n * 2 + 1, m + 1, e);
    }

    private static int query(int k, int n, int s, int e) {
        if (s == e)
            return s;
        int m = s + e >>> 1;
        if (tree[n * 2] >= k)
            return query(k, n * 2, s, m);
        return query(k - tree[n * 2], n * 2 + 1,m + 1, e);
    }

}

class FastIO {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    FastIO() {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }

    int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    long nextLong() throws IOException {
        return Long.parseLong(next());
    }

    double nextDouble() throws IOException {
        return Double.parseDouble(next());
    }

    String nextLine() throws IOException {
        return br.readLine();
    }

    void write(double d) throws IOException {
        bw.write(String.valueOf(d));
        close();
    }

    void write(char c) throws IOException {
        bw.write(c);
        close();
    }

    void write(int i) throws IOException {
        bw.write(String.valueOf(i));
        close();
    }

    void write(long l) throws IOException {
        bw.write(String.valueOf(l));
        close();
    }

    void write(StringBuilder sb) throws IOException {
        bw.write(sb.toString().trim());
        close();
    }

    void write(String str) throws IOException {
        bw.write(str.trim());
        close();
    }

    void close() throws IOException {
        bw.close();
        br.close();
    }
}