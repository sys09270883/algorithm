/*
https://www.acmicpc.net/problem/11559
[문제]
뿌요뿌요의 룰은 다음과 같다.

필드에 여러 가지 색깔의 뿌요를 놓는다. 뿌요는 중력의 영향을 받아 아래에 바닥이나 다른 뿌요가 나올 때까지 아래로 떨어진다.

뿌요를 놓고 난 후, 같은 색 뿌요가 4개 이상 상하좌우로 연결되어 있으면 연결된 같은 색 뿌요들이 한꺼번에 없어진다.

뿌요들이 없어지고 나서 위에 다른 뿌요들이 있다면, 역시 중력의 영향을 받아 차례대로 아래로 떨어지게 된다.

아래로 떨어지고 나서 다시 같은 색의 뿌요들이 4개 이상 모이게 되면 또 터지게 되는데, 터진 후 뿌요들이 내려오고 다시 터짐을 반복할 때마다 1연쇄씩 늘어난다.

터질 수 있는 뿌요가 여러 그룹이 있다면 동시에 터져야 하고 여러 그룹이 터지더라도 한번의 연쇄가 추가된다.

남규는 최근 뿌요뿌요 게임에 푹 빠졌다. 
이 게임은 1:1로 붙는 대전게임이라 잘 쌓는 것도 중요하지만, 상대방이 터뜨린다면 연쇄가 몇 번이 될지 바로 파악할 수 있는 능력도 필요하다. 
하지만 아직 실력이 부족하여 남규는 자기 필드에만 신경 쓰기 바쁘다. 상대방의 필드가 주어졌을 때, 연쇄가 몇 번 연속으로 일어날지 계산하여 남규를 도와주자!

[입력]
12*6의 문자가 주어진다.

이때 .은 빈공간이고 .이 아닌것은 각각의 색깔의 뿌요를 나타낸다.

R은 빨강, G는 초록, B는 파랑, P는 보라, Y는 노랑이다.(모두 대문자로 주어진다.)

입력으로 주어지는 필드는 뿌요들이 전부 아래로 떨어진 뒤의 상태(즉 뿌요 아래에 빈 칸이 있는 경우는 없음) 이다.

[출력]
현재 주어진 상황에서 몇연쇄가 되는지 출력하라. (하나도 터지지 않는다면 0을 출력하면 된다.)

[풀이]
DFS, BFS로 그래프를 탐색하면서 4칸 이상 붙어있는 경우 '.'으로 바꿔주고 업데이트한다.
업데이트 후 나머지 부분을 탐색하며, 더 이상 탐색할 수 없는 경우 탈출한다.

 + 구현이 쉽지 않았고, 문제의 연쇄조건에 대해 착각해서 한참 헤맸다... 문제 확인 잘 하자..

*/
import java.io.*;
import java.util.*;

public class Main {

	static char[][] map;
	static boolean[][] visited;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static ArrayList<Node> list;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		map = new char[12][6];
		visited = new boolean[12][6];
		String input = null;
		list = new ArrayList<Node>();
		int res = 0;
		
		for (int i = 0; i < 12; i++) {
			input = br.readLine();
			for (int j = 0; j < 6; j++) {
				map[i][j] = input.charAt(j);
			}
		}
		
		while (true) {
			boolean flag = false;
			
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					if (!visited[i][j] && map[i][j] != '.') {
						DFS(i, j, map[i][j]);
						
						if (list.size() >= 4) {
							flag = true;
							for (Node n : list) {
								map[n.x][n.y] = '.';
							}
						}
						
						list.clear();
					}
				}
			}
			
			if (!flag)
				break;
			
			res++;
			update();
		}
		
		bw.write(String.valueOf(res));
		bw.close();
		br.close();
	}
	
	private static void DFS(int x, int y, char ch) {
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || ny < 0 || nx >= 12 || ny >= 6)
				continue;
			
			if (visited[nx][ny] || map[nx][ny] != ch)
				continue;
			
			visited[nx][ny] = true;
			list.add(new Node(nx, ny));
			DFS(nx, ny, ch);
		}
	}
	
	private static void update() {
		for (int i = 0; i < 6; i++) {
			for (int j = 10; j >= 0; j--) {
				for (int k = 11; k > j; k--) {
					if (map[j][i] != '.' && map[k][i] == '.') {
						map[k][i] = map[j][i];
						map[j][i] = '.';
						break;
					}
				}
			}
		}
		
		for (boolean[] row : visited) {
			Arrays.fill(row, false);
		}
	}
	
}

class Node {
	int x, y;

	public Node(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
}