import java.util.*;
public class MaxFlowDinic {

  static class Edge {
    int to, from, capacity, f;

    public Edge(int to, int from, int capacity) {
      this.to = to;
      this.from = from;
      this.capacity = capacity;
    }
  }

  public  List<Edge>[] createGraph(int nodes) {
    @SuppressWarnings("unchecked")
	List<Edge>[] graph = new List[nodes];
    for (int i = 0; i < nodes; i++)
      graph[i] = new ArrayList<>();
    return graph;
  }

  public  void addEdge(List<Edge>[] graph, int s, int to, int capacity) {
    graph[s].add(new Edge(to, graph[to].size(), capacity));
    graph[to].add(new Edge(s, graph[s].size() - 1, 0));
  }
  
   boolean dinicBfs(List<Edge>[] graph, int source, int destination, int[] distance) {
    Arrays.fill(distance, -1);
    distance[source] = 0;
    int[] Queue = new int[graph.length];
    int sizeQ = 0;
    Queue[sizeQ++] = source;
    for (int i = 0; i < sizeQ; i++) {
      int u = Queue[i];
      for (Edge e : graph[u]) {
        if (distance[e.to] < 0 && e.f < e.capacity) {
          distance[e.to] = distance[u] + 1;
          Queue[sizeQ++] = e.to;
        }
      }
    }
    return distance[destination] >= 0;
  }

   int dinicDfs(List<Edge>[] graph, int[] ptr, int[] distance, int destination, int u, int f) {
    if (u == destination)
      return f;
    for (; ptr[u] < graph[u].size(); ++ptr[u]) {
      Edge e = graph[u].get(ptr[u]);
      if (distance[e.to] == distance[u] + 1 && e.f < e.capacity) {
        int df = dinicDfs(graph, ptr, distance, destination, e.to, Math.min(f, e.capacity - e.f));
        if (df > 0) {
          e.f += df;
          graph[e.to].get(e.from).f -= df;
          return df;
        }
      }
    }
    return 0;
  }

  public  int maxFlow(List<Edge>[] graph, int source, int destination) {
    int flow = 0;
    int[] distance = new int[graph.length];
    while (dinicBfs(graph, source, destination, distance)) {
      int[] ptr = new int[graph.length];
      while (true) {
        int df = dinicDfs(graph, ptr, distance, destination, source, Integer.MAX_VALUE);
        if (df == 0)
          break;
        flow += df;
      }
    }
    return flow;
  }
  
  public void fromStoA(List<Edge>[] graph, HashMap<Integer, Integer> bagA) {
	  if(!bagA.isEmpty()) {
		  for(Integer node : bagA.keySet()) {
			  addEdge(graph, 0, node, bagA.get(node));
		  }
	  }
  }
  
  public  void addBagTypeA(List<Edge>[] graph, HashMap<Integer, Integer> bags, HashMap<Integer, Integer> transport) {
	  if(!transport.isEmpty() && !bags.isEmpty()) {
		  for(Integer bagNode : bags.keySet()) {
			  for(Integer node : transport.keySet()) {
				  addEdge(graph, bagNode, node, 1);
			  }
		  }
	  }
  }
  public  void addBagNotA(List<Edge>[] graph, int s, HashMap<Integer, Integer> transport) {
	if(!transport.isEmpty() && s != 0 ) {	  
	  for(Integer node : transport.keySet()) {
			  addEdge(graph, s, node, Integer.MAX_VALUE);
		  }
	  }
  }
  
  public  void transportToSink(List<Edge>[] graph, int sink, HashMap<Integer, Integer> transport) {
	  if(!transport.isEmpty() ) {
		  for(Integer node : transport.keySet()) {
			  addEdge(graph, node, sink, transport.get(node));
		  }
	  }
  }
  
  
}
