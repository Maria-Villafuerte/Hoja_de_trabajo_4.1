// Graph, implemented with an Adjacency IList
// (c) 1998, 2001 duane a. bailey

package structure;
import java.util.Iterator;

/**
 * Implementation of graph using adjacency lists.
 * Edges are stored in lists off of vertex structure.
 * Class is abstract: you must use GraphListDirected or 
 * GraphListUndirected to construct particular instances of graphs.
 *
 * Typical usage:
 * <pre>
 *     Graph g = new GraphListDirected();
 *     g.add("harry");
 *     g.add("sally");
 *     g.addEdge("harry","sally","friendly");
 *     ...
 * </pre>
 *
 * @version $Id: GraphList.java 8 2006-08-02 19:03:11Z bailey $
 * @author, 2001 duane a. bailey and kimberly tabtiang
 * @see GraphListDirected
 * @see GraphListUndirected
 * @see GraphMatrix
 */
abstract public class GraphList extends AbstractStructure implements Graph
{
    /**
     * Map associating vertex labels with vertex structures.
     */
    protected Map dict; // label to vertex dictionary
    /**
     * Whether or not graph is directed.
     */
    protected boolean directed; // is graph directed?

    /**
     * Constructor of directed/undirected GraphList. Protected constructor.
     *
     * @param dir True if graph is to be directed.
     */
    protected GraphList(boolean dir)
    {
        dict = new Hashtable();
        directed = dir;
    }
    /**
     * Add a vertex to the graph.
     *
     * @pre label is a non-null label for vertex
     * @post a vertex with label is added to graph;
     *       if vertex with label is already in graph, no action
     * 
     * @param label Label of the vertex; should be non-null.
     */
    public void add(Object label)
    {
        if (dict.containsKey(label)) return; // vertex exists
        GraphListVertex v = new GraphListVertex(label);
        dict.put(label,v);
    }

    /**
     * Add an edge between two vertices within the graph.  Edge is directed
     * iff graph is directed.  Duplicate edges are silently replaced.
     * Labels on edges may be null.
     *
     * @pre vtx1 and vtx2 are labels of existing vertices
     * @post an edge (possibly directed) is inserted between
     *       vtx1 and vtx2.
     * 
     * @param vtx1 First (or source, if directed) vertex.
     * @param vtx2 Second (or destination, if directed) vertex.
     * @param label Label associated with the edge.
     */
    abstract public void addEdge(Object v1, Object v2, Object label);

    /**
     * Remove a vertex from the graph.  Associated edges are also 
     * removed.  Non-vertices are silently ignored.
     *
     * @pre label is non-null vertex label
     * @post vertex with "equals" label is removed, if found
     * 
     * @param label The label of the vertex within the graph.
     * @return The label associated with the vertex.
     */
    abstract public Object remove(Object label);

    /**
     * Remove possible edge between vertices labeled vLabel1 and vLabel2.
     * Directed edges consider vLabel1 to be the source.
     *
     * @pre vLabel1 and vLabel2 are labels of existing vertices
     * @post edge is removed, its label is returned
     * 
     * @param vLabel1 First (or source, if directed) vertex.
     * @param vLabel2 Second (or destination, if directed) vertex.
     * @return The label associated with the edge removed.
     */
    abstract public Object removeEdge(Object vLabel1, Object vLabel2);

    /**
     * Get reference to actual label of vertex.  Vertex labels are matched
     * using their equals method, which may or may not test for actual
     * equivalence.  Result remains part of graph.
     *
     * @post returns actual label of indicated vertex
     * 
     * @param label The label of the vertex sought.
     * @return The actual label, or null if none is found.
     */
    public Object get(Object label)
    {
        Assert.condition(dict.containsKey(label), "Vertex exists");
        return ((GraphListVertex)dict.get(label)).label();
    }

    /**
     * Get reference to actual edge.  Edge is identified by
     * the labels on associated vertices.  If edge is directed, the
     * label1 indicates source.
     *
     * @post returns actual edge between vertices
     * 
     * @param label1 The first (or source, if directed) vertex.
     * @param label2 The second (or destination, if directed) vertex.
     * @return The edge, if found, or null.
     */
    public Edge getEdge(Object label1, Object label2)
    {
        Assert.condition(dict.containsKey(label1), "Vertex exists");
        Edge e = new Edge(get(label1),get(label2), null, directed); 
        return ((GraphListVertex) dict.get(label1)).getEdge(e);
    }

    /**
     * Test for vertex membership.
     *
     * @post returns true iff vertex with "equals" label exists
     * 
     * @param label The label of the vertex sought.
     * @return True iff vertex with matching label is found.
     */
    public boolean contains(Object label)
    {
        return dict.containsKey(label);   
    }

    /**
     * Test for edge membership.  If edges are directed, vLabel1
     * indicates source.
     *
     * @post returns true iff edge with "equals" label exists
     * 
     * @param vLabel1 First (or source, if directed) vertex.
     * @param vLabel2 Second (or destination, if directed) vertex.
     * @return True iff the edge exists within the graph.
     */
    public boolean containsEdge(Object vLabel1, Object vLabel2)
    {
        Assert.condition(dict.containsKey(vLabel1), "Vertex exists");
        Edge e = new Edge(vLabel1, vLabel2, null, directed); 
        return ((GraphListVertex) dict.get(vLabel1)).containsEdge(e);
    }

    /**
     * Test and set visited flag of vertex.
     *
     * @post sets visited flag on vertex, returns previous value
     * 
     * @param label Label of vertex to be visited.
     * @return Previous value of visited flag on vertex.
     */
    public boolean visit(Object label)
    {
        return ((GraphListVertex)dict.get(label)).visit();
    }
    
    /**
     * Test and set visited flag of edge.
     *
     * @pre sets visited flag on edge; returns previous value
     * 
     * @param e Edge object that is part of graph.
     * @return Previous value of the Edge's visited flag.
     */
    public boolean visitEdge(Edge e)
    {
        return e.visit();
    }

    /**
     * Return visited flag of vertex.
     *
     * @post returns visited flag on labeled vertex
     * 
     * @param label Label of vertex.
     * @return True if vertex has been visited.
     */
    public boolean isVisited(Object label)
    {
        return ((GraphListVertex)dict.get(label)).isVisited();
    }

    /**
     * Return visited flag of edge.
     *
     * @post returns visited flag on edge between vertices
     * 
     * @param e Edge of graph to be considered.
     * @return True if the edge has been visited.
     */
    public boolean isVisitedEdge(Edge e)
    {
        return e.isVisited();
    }

    /**
     * Clear visited flags of edges and vertices.
     *
     * @post resets visited flags to false
     */
    public void reset()
    {
        // reset the vertices
        Iterator vi = dict.values().iterator();
        while (vi.hasNext())
        {
            Vertex vtx = (Vertex)vi.next();
            vtx.reset();
        }
        // reset the edges
        Iterator ei = edges();
        while (ei.hasNext())
        {
            Edge e = (Edge)ei.next();
            e.reset();
        }
    }

    /**
     * Determine number of vertices within graph.
     *
     * @post returns the number of vertices in graph
     * 
     * @return The number of vertices within graph.
     */
    public int size()
    {
        return dict.size(); 
    }

    /**
     * Determine out degree of vertex.
     *
     * @pre label labels an existing vertex
     * @post returns the number of vertices adjacent to vertex
     *
     * @param label Label associated with vertex.
     * @return The number of edges with this vertex as source.
     */
    public int degree(Object label)
    {
        Assert.condition(dict.containsKey(label), "Vertex exists.");
        return ((GraphListVertex) dict.get(label)).degree();
    }

    /**
     * Determine the number of edges in graph.
     *
     * @post returns the number of edges in graph
     * 
     * @return Number of edges in graph.
     */
    abstract public int edgeCount();

    /**
     * Construct vertex iterator.  Vertices are not visited in 
     * any guaranteed order.
     *
     * @post returns iterator across all vertices of graph
     * 
     * @return AbstractIterator traversing vertices in graph.
     */
    public Iterator iterator()
    {
        return dict.keySet().iterator();
    }

    /**
     * Construct an adjacent vertex iterator.   Adjacent vertices
     * (those on destination of edge, if directed) are considered,
     * but not in any guaranteed order.
     *
     * @pre label is label of vertex in graph
     * @post returns iterator over vertices adj. to vertex
     *       each edge beginning at label visited exactly once
     * 
     * @param label Label of the vertex.
     * @return Iterator traversing the adjacent vertices of labeled vertex.
     */
    public Iterator neighbors(Object label)
    {
        // return towns adjacent to vertex labeled label
        Assert.condition(dict.containsKey(label), "Vertex exists");
        return ((GraphListVertex) dict.get(label)).adjacentVertices();
    }

    /**
     * Construct an iterator over all edges.  Every directed/undirected
     * edge is considered exactly once.  Order is not guaranteed.
     *
     * @post returns iterator across edges of graph
     *       iterator returns edges; each edge visited once
     * 
     * @return Iterator over edges.
     */
    public Iterator edges()
    {
        return new GraphListEIterator(dict);
    }

    /**
     * Remove all vertices (and thus, edges) of the graph.
     *
     * @post removes all vertices from graph
     */
    public void clear()
    {
        dict.clear();
    }

    /**
     * Determine if graph is empty.
     *
     * @post returns true if graph contains no vertices
     * 
     * @return True iff there are no vertices in graph.
     */
    public boolean isEmpty()
    {
      return dict.isEmpty();
    }

    /**
     * Determine if graph is directed.
     *
     * @post returns true if edges of graph are directed
     * 
     * @return True iff the graph is directed.
     */
    public boolean isDirected()
    {
        return directed;
    }
}
