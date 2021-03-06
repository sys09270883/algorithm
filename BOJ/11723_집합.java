import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();

    public static void main(String... args) throws IOException {
        int M = io.nextInt(), x;
        int S = 0;
        String input = null;
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < M; i++) {
            input = io.next();

            if (input.equals("add")) {
                x = io.nextInt();
                S |= 1 << x;
            }
            else if (input.equals("remove")) {
                x = io.nextInt();
                S &= ~(1 << x);
            }
            else if (input.equals("check")) {
                x = io.nextInt();
                res.append((S & (1 << x)) == 0 ? "0\n" : "1\n");
            }
            else if (input.equals("toggle")) {
                x = io.nextInt();
                S ^= (1 << x);
            }
            else if (input.equals("all"))
                S = (1 << 21) - 1;
            else
                S = 0;
        }

        io.write(res);
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

    String next() {
        while (st == null || !st.hasMoreTokens()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return st.nextToken();
    }

    int nextInt() {
        return Integer.parseInt(next());
    }

    long nextLong() {
        return Long.parseLong(next());
    }

    double nextDouble() {
        return Double.parseDouble(next());
    }

    String nextLine() {
        String str = null;
        try {
            str = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return str;
    }

    void write(char c) throws IOException {
        bw.write(c);
        close();
    }

    void write(double d) throws IOException {
        bw.write(String.valueOf(d));
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

    void write(char[] str) throws IOException {
        int len = str.length;
        for (int i = 0; i < len; i++) {
            bw.write(str[i]);
        }
        close();
    }

    void close() throws IOException {
        bw.close();
        br.close();
    }
}