package P2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

import P1.graph.ConcreteVerticesGraph;

/**
 * FriendshipGraph
 */
public class FriendshipGraph extends ConcreteVerticesGraph<Person> {

	/**
	 * <p>
	 * Abstraction function:
	 * </p>
	 * f(G) = ({persons}, {edges}), represent the friendship graph
	 *
	 * <p>
	 * Representation invariant:
	 * </p>
	 * All edges' weights are one.
	 *
	 * <p>
	 * Safety from rep exposure:
	 * </p>
	 * all fields are private and final return copied vertices when call vertices()
	 * elements of vertices and edges are immutable, so will not be changed in
	 * returned collections.
	 */

	/**
	 * FriendshipGraph Constructor
	 */
	public FriendshipGraph() {
		super();
	}

	/**
	 * Check representation.
	 */
	protected void checkRep() {
		super.checkRep();
		for (Person person : vertices()) {
			for (Integer weight : sources(person).values()) {
				assert weight == 1;
			}
		}
	}

	/**
	 * Add a person into FriendshipGraph
	 *
	 * @param person the person to add
	 * @return true if the person added successfully
	 */
	public boolean addVertex(Person person) {
		return add(person);
	}

	/**
	 * Add a directed edge into FriendshipGraph
	 *
	 * @param fromPerson the from person of the edge
	 * @param toPerson   the to person of the edge
	 * @return true if the edge added successfully
	 */
	public boolean addEdge(Person fromPerson, Person toPerson) {
		set(fromPerson, toPerson, 1);
		checkRep();
		return true;
	}

	/**
	 * Calc the distance between two person
	 *
	 * @param fromPerson the from person of the edge
	 * @param toPerson   the to person of the edge
	 * @return the distance from fromPerson to toPerson, -1 if not connected
	 */
	public int getDistance(Person fromPerson, Person toPerson) {
		if (fromPerson.equals(toPerson))
			return 0;

		Map<Person, Integer> dist = new HashMap<>();
		dist.put(fromPerson, 0);
		Queue<Person> queue = new LinkedList<>();
		queue.add(fromPerson);
		while (!queue.isEmpty()) {
			Person u = queue.poll();
			int newDist = dist.get(u) + 1;
			for (Map.Entry<Person, Integer> e : targets(u).entrySet()) {
				Person v = e.getKey();
				if (v.equals(toPerson))
					return newDist;
				if (dist.containsKey(v))
					continue;
				dist.put(v, newDist);
				queue.add(v);
			}
		}
		return -1;
	}

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
						for (Person person : graph.vertices())
							System.out.println(person.getName());
						break;
					case "exit":
						return;
					default:
						System.out.println("错误指令");
				}
			}
		}
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

}
