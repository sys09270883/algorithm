/*
https://www.acmicpc.net/problem/1715
[문제]
정렬된 두 묶음의 숫자 카드가 있다고 하자. 각 묶음의 카드의 수를 A, B라 하면 보통 두 묶음을 합쳐서 하나로 만드는 데에는 A+B 번의 비교를 해야 한다. 
이를테면, 20장의 숫자 카드 묶음과 30장의 숫자 카드 묶음을 합치려면 50번의 비교가 필요하다.

매우 많은 숫자 카드 묶음이 책상 위에 놓여 있다. 이들을 두 묶음씩 골라 서로 합쳐나간다면, 고르는 순서에 따라서 비교 횟수가 매우 달라진다. 
예를 들어 10장, 20장, 40장의 묶음이 있다면 10장과 20장을 합친 뒤, 합친 30장 묶음과 40장을 합친다면 (10+20)+(30+40) = 100번의 비교가 필요하다. 
그러나 10장과 40장을 합친 뒤, 합친 50장 묶음과 20장을 합친다면 (10+40)+(50+20) = 120 번의 비교가 필요하므로 덜 효율적인 방법이다.

N개의 숫자 카드 묶음의 각각의 크기가 주어질 때, 최소한 몇 번의 비교가 필요한지를 구하는 프로그램을 작성하시오.

[입력]
첫째 줄에 N이 주어진다. (1≤N≤100,000) 이어서 N개의 줄에 걸쳐 숫자 카드 묶음의 각각의 크기가 주어진다.

[출력]
첫째 줄에 최소 비교 횟수를 출력한다. (21억 이하)

[풀이]
작은 값들의 합을 먼저 해야하므로 우선순위큐로 작은값들을 더해주고 결과값에 더하고, 다시 우선순위큐에 넣어준다.
우선순위큐의 크기가 1일 때까지 반복한다.

*/
import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine()), res = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		
		for (int i = 0; i < N; i++) {
			pq.add(Integer.parseInt(br.readLine()));
		}
		
		for (int i = 0; i < N - 1; i++) {
			int tmp = pq.poll();
			tmp += pq.poll();
			res += tmp;
			pq.add(tmp);
		}
		
		bw.write(String.valueOf(res));
		bw.close();
		br.close();
	}
	
}
