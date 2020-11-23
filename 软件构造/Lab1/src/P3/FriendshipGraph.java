package P3;


import java.util.TreeMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

/**
 * FriendshipGraph
 */
public class FriendshipGraph {

	private int totalPerson;
	private Map<String, Integer> personMap;
	private Map<Integer, Set<Integer>> adjacencySet;

	public static void main(String[] args) throws Exception {
		FriendshipGraph graph = new FriendshipGraph();

		try (Scanner scanner = new Scanner(System.in);) {
			printHelp();
			while (true) {
				System.out.print("输入指令：");
				if (!scanner.hasNext())
					break;
				String instruction = scanner.nextLine();
				if (instruction.isEmpty())
					continue;
				String[] parms = instruction.split("\\s+");
				switch (parms[0]) {
					case "addp":
						for (int i = 1; i < parms.length; ++i)
							graph.addVertex(new Person(parms[i]));
						break;
					case "adde":
						if (parms.length != 3) {
							System.out.println("adde 格式应当形如adde person1 person2");
						} else {
							Person a = new Person(parms[1]);
							Person b = new Person(parms[2]);
							graph.addEdge(a, b);
							graph.addEdge(b, a);
						}
						break;
					case "dist":
						if (parms.length != 3) {
							System.out.println("dist 格式应当形如dist person1 person2");
						} else {
							Person a = new Person(parms[1]);
							Person b = new Person(parms[2]);
							System.out.println(graph.getDistance(a, b));
						}
						break;
					case "demo":
						runDemo(graph);
						break;
					case "help":
						printHelp();
						break;
					case "list":
						for (String name : graph.getPeopleNameSet())
							System.out.println(name);
						break;
					case "exit":
						return;
					default:
						System.out.println("错误指令");
				}
			}
		}
	}

	/**
	 * FriendshipGraph Constructor
	 */
	public FriendshipGraph() {
		totalPerson = 0;
		personMap = new HashMap<>();
		adjacencySet = new TreeMap<>();
	}

	/**
	 * Add a person into FriendshipGraph
	 *
	 * @param person the person to add
	 * @return the uid of the person
	 * @throws RuntimeException the people is already in this graph
	 */
	public int addVertex(Person person) {
		if (containsPerson(person))
			throw new RuntimeException("Each person should hava a unique name");
		int id = totalPerson;
		++totalPerson;
		personMap.put(person.getName(), id);
		adjacencySet.put(id, new TreeSet<>());
		return id;
	}

	/**
	 * Add a directed edge into FriendshipGraph
	 *
	 * @param fromPerson the from person of the edge
	 * @param toPerson   the to person of the edge
	 */
	public void addEdge(Person fromPerson, Person toPerson) {
		int from = getPersonUid(fromPerson), to = getPersonUid(toPerson);

		// someone is not in the graph
		if (from == -1 || to == -1)
			return;

		adjacencySet.get(from).add(to);
	}

	/**
	 * Calc the distance between two person
	 *
	 * @param fromPerson the from person of the edge
	 * @param toPerson   the to person of the edge
	 */
	public int getDistance(Person fromPerson, Person toPerson) {
		// the same person
		if (fromPerson.getName() == toPerson.getName())
			return 0;

		int from = getPersonUid(fromPerson), to = getPersonUid(toPerson);
		// one of the people is not in this graph.
		if (from == -1 || to == -1)
			return -1;

		// BFS
		int[] dist = new int[totalPerson];
		Queue<Integer> queue = new LinkedList<>();

		Arrays.fill(dist, -1);
		queue.add(from);
		dist[from] = 0;
		while (!queue.isEmpty()) {
			int u = queue.poll();
			for (int v : adjacencySet.get(u)) {
				if (dist[v] != -1)
					continue;
				if (v == to)
					return dist[u] + 1;
				dist[v] = dist[u] + 1;
				queue.add(v);
			}
		}
		return -1;
	}

	/**
	 * Check whether the person is in the graph
	 *
	 * @param person the person to check
	 * @return whether the person is in the graph
	 */
	public boolean containsPerson(Person person) {
		return personMap.containsKey(person.getName());
	}

	/**
	 * Check whether the edge is in the graph
	 *
	 * @param fromPerson the from person of the edge
	 * @param toPerson   the to person of the edge
	 * @return whether the edge is in the graph
	 */
	public boolean containsEdge(Person fromPerson, Person toPerson) {
		int from = getPersonUid(fromPerson), to = getPersonUid(toPerson);
		if (from == -1 || to == -1)
			return false;
		return adjacencySet.get(from).contains(to);
	}

	private int getPersonUid(Person person) {
		return personMap.getOrDefault(person.getName(), -1);
	}

	private static void printHelp() {
		System.out.println("指令说明");
		System.out.println("\taddp：添加Peerson，可以添加多个（使用空格隔开），不能有重复");
		System.out.println("\tadde：添加Edge，添加一条无向边，格式形如 adde person1 person2");
		System.out.println("\tdist：计算distance，dist person1 person2");
		System.out.println("\tlist：列出所有图中Person");
		System.out.println("\tdemo：显示demo");
		System.out.println("\thelp：显示指令说明");
		System.out.println("\texit：退出");
		System.out.flush();
	}

	private static void runDemo(FriendshipGraph graph) {
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross");
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");

		graph.addVertex(rachel);
		System.out.println("Add Person Rachel");
		graph.addVertex(ross);
		System.out.println("Add Person Ross");
		graph.addVertex(ben);
		System.out.println("Add Person Ben");
		graph.addVertex(kramer);
		System.out.println("Add Person Kramer");

		System.out.println("Add Edge between Rachel and Ross");
		graph.addEdge(rachel, ross);
		graph.addEdge(ross, rachel);

		System.out.println("Add Edge between Ben and Ross");
		graph.addEdge(ross, ben);
		graph.addEdge(ben, ross);

		System.out.println("The distance between Rachel and Ross:" + graph.getDistance(rachel, ross));
		System.out.println("The distance between Rachel and Ben:" + graph.getDistance(rachel, ben));
		System.out.println("The distance between Rachel and Rachel:" + graph.getDistance(rachel, rachel));
		System.out.println("The distance between Rachel and Kramer:" + graph.getDistance(rachel, kramer));
		System.out.flush();
	}

	private Set<String> getPeopleNameSet() {
		return personMap.keySet();
	}
}
