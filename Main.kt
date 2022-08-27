class Node<A>(var value: A, var next: Node<A>? = null)
// Making next immutable is crucial.

class LinkedList<A> {
    var head: Node<A>? = null
    // The head is the front of the list.

    fun lastNode(): Node<A>? {
        var node = head
        return if (node != null) {
            while (node?.next != null) node = node.next
            node
            // Returns the node when the next one is null.
        } else null
    }

    fun append(value: A) {
        val newNode = Node(value)
        val lastNode = this.lastNode()
        if (lastNode != null) lastNode.next = newNode else head = newNode
        // Make a new list of all the old things then the new thing.
    }

    fun push(value: A) {
        head = Node(value = value, next = head)
        // Make a new list of the new thing then all the old things.
    }

    override fun toString(): String {
        var node = head
        var list = " "

        while (node != null) {
            list = list.plus("${node.value}")
            node = node.next
            if (node != null) list = list.plus(", ")
        }
        return list
    }
}

fun main(args: Array<String>) {
    println("Hello World!")
    println("This is a list of spices to use:")

    val spiceList = LinkedList<String>()

    spiceList.append("Salt")
    println(spiceList)

    spiceList.append("Garlic")
    println(spiceList)

    spiceList.push("Habenero")
    println(spiceList)

    spiceList.append("Jalepeno")
    println(spiceList)

    spiceList.push("Cumin")
    println(spiceList)

    spiceList.append("Sage")
    println(spiceList)

    spiceList.push("Paprika")
    println(spiceList)
}