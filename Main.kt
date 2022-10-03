class Node<A>(var value: A, var next: Node<A>? = null, var previous: Node<A>? = null)

class LinkedList<A> {
    var head: Node<A>? = null
    // The head is the first node on the list.

    var tail: Node<A>? = null
    // The tail is the final node on the list.

    fun lastNode(): Node<A>? {
        var node = head
        return if (node != null) {
            while (node?.next != null)
                node = node.next
            tail = node
            node
            // Returns the node when the next one is null.

        } else null
    }

    fun append(value: A) {
        val lastNode = this.lastNode()
        val newNode = Node(value)
        tail = newNode
        if (lastNode != null) {
            lastNode.next = newNode
            newNode.previous = lastNode
        }
        else head = newNode
        // Finds the last node and points its next at the new node.
    }

    fun push(value: A) {
        head = Node(value = value, next = head)
        val node = head?.next
        node?.previous = head
        // Makes a new node and points the next at the head of the list.
    }

    fun insertAfter(prevNode: Node<A>, value: A) {
        val newNode = Node(value)
        val thirdInLine = prevNode.next
        prevNode.next = newNode
        newNode.next = thirdInLine
        newNode.previous = prevNode
        thirdInLine?.previous = newNode
        if (thirdInLine == null) tail = newNode
        // Changes next pointer of the chosen node to the new node which points at the node previously after the chosen.
    }

    fun nodeAtIndex(index: Int) : Node<A>? {
        if (index >= 0) {
            var node = head
            var i = index
            while (node != null) {
                if (i == 0) return node
                i -= 1
                node = node.next
                // Returns the node at a specific index by counting down as it moves to the next nodes.
            }
        }
        return null
    }

    fun insertAtIndex(index: Int, value: A) {
        val newNode = Node(value)
        val node = nodeAtIndex(index)
        val thirdInLine = node?.next
        if (node != null) {
            newNode.next = node.next
            node.next = newNode
            newNode.previous = node
            thirdInLine?.previous = newNode
            if (thirdInLine == null) tail = newNode
            // Makes a new node and changes pointers based on index.
        } else head = newNode
    }

    fun removeNode(deletedNode: Node<A>) {
        val nodeNext = deletedNode.next
        val nodePrev = deletedNode.previous
        if (nodePrev != null) {
            nodePrev.next = nodeNext
            nodeNext?.previous = nodePrev
            if (nodeNext == null) tail = nodePrev
        }else head = nodeNext
        // Changes the next pointer to point at the node after the node to be deleted.
    }

    fun removeAtIndex(index: Int): Unit? {
        val node = nodeAtIndex(index)
        return if (node != null) removeNode(node)
        else null
        // Picks node at an index and calls remove function.
    }

    fun getCountIterative(): Int {
        var temp = head
        var count = 0
        while (temp != null) {
            count++
            temp = temp.next
        }
        return count
        // Counts one for every node.
    }

    fun countRecursive(node: Node<A>?): Int {
        return if (node == null) 0
        else 1 + countRecursive(node.next)
        // Counts the total.  Is it really recursive?
    }

    fun getCountRecursive(): Int {
        return countRecursive(head)
    }

    fun removeAll() {
        head = null
    }

    fun connectLists(firstList: LinkedList<A>, secondList: LinkedList<A>,combinedList: LinkedList<A>): LinkedList<A> {
        combinedList.head = firstList.head
        // Makes the new list start as a copy of the first.

        combinedList.lastNode()
        combinedList.tail?.next = secondList.head
        // Finds the tail of the new list and sets it next to point at the head of the second list.

        combinedList.tail?.next?.previous = combinedList.tail
        // Points previous of the second list head at the tail of the new list.

        combinedList.lastNode()
        return combinedList
    }

    fun remark(index: Int, value: A) {
        val oldNode = nodeAtIndex(index)
        val newNode = Node(value)
        val oldPrev = oldNode?.previous
        val oldNext = oldNode?.next
        if (oldNode != null) {
            oldPrev?.next = newNode
            oldNext?.previous = newNode
            newNode.previous = oldPrev
            newNode.next = oldNext
            // Connects the next and previous pointers of the previous, new and next nodes into a new list.

            lastNode()
        } else head = newNode
    }

    fun middleNode(start: Node<A>?, last: Node<A>?): Node<A> {
        var slow = start
        var fast = start
        while (fast?.next != last?.next) {
            fast = fast?.next
            if (fast?.next != last?.next) {
                slow = slow?.next!!
                fast = fast?.next
                // Fast goes next twice until it hits null. Slow went half speed so it stops halfway.
            }
        }
        return slow!!
    }

    fun binarySearch() {
        // Think of it as a binary search if the user checks the boolean values.

        var start = head
        var last = tail
        var middle : Node<A>
        var userInput : String
        println("Pick out an ingredient.")
        do {
            middle = middleNode(start, last)
            println("The middle is ${middle.value}. Is it correct or is it before or after?")
            userInput = readLine().toString()

            when (userInput) {
                "before" -> last = middle.previous
                "after" -> start = middle.next
            }
        } while (userInput != "correct")
        println("correct!")
    }

    override fun toString(): String {
        var node = head
        var list = ""

        while (node != null) {
            list = list.plus("${node.value}")
            node = node.next
            if (node != null) list = list.plus(", ")
            // Puts each node on the list until the null at the end.
        }
        return list
    }
}

fun main() {
    println("Hello World!")
    println("This is a list of spices to use:")

    val spiceList = LinkedList<String>()

    spiceList.append("Salt")
    println(spiceList)
    spiceList.append("Garlic")
    println(spiceList)
    spiceList.push("Habanero")
    println(spiceList)
    spiceList.append("Jalapeno")
    println(spiceList)
    spiceList.push("Cumin")
    println(spiceList)
    spiceList.append("Sage")
    println(spiceList)
    spiceList.push("Paprika")
    println(spiceList)

    spiceList.insertAfter(spiceList.head?.next?.next!!, "Cayenne")
    println(spiceList)

    spiceList.insertAtIndex(3, "Oregano")
    println(spiceList)

    println("The list is now " + spiceList.getCountRecursive() + " items long.")

    println("1st item: ${spiceList.nodeAtIndex(0)?.value}")
    println("2nd item: ${spiceList.nodeAtIndex(1)?.value}")
    println("3rd item: ${spiceList.nodeAtIndex(2)?.value}")

    println("who really calls salt a spice?")
    spiceList.removeAtIndex(5)
    println(spiceList)

    println("The list is now " + spiceList.getCountIterative() + " items long.")

    println("The last node is: ${spiceList.tail?.value}")
    println("Penultimate: ${spiceList.tail?.previous?.value}")

    spiceList.removeAll()
    println("The list is now " + spiceList.getCountRecursive() + " items long.")

    println("Nah, let's bring it all back, make another list and connect them for a secret recipe!")
    spiceList.append("Paprika")
    spiceList.append("Cumin")
    spiceList.append("Habanero")
    spiceList.append("Cayenne")
    spiceList.append("Oregano")
    spiceList.append("Garlic")
    spiceList.append("Jalapeno")
    spiceList.append("Sage")
    println(spiceList)

    val ingredientList = LinkedList<String>()

    println("And the ingredients are: ")
    ingredientList.push("Water")
    ingredientList.push("Salt")
    ingredientList.push("Tomato")
    ingredientList.push("Kidney Beans")
    ingredientList.push("Black Beans")
    ingredientList.push("Pinto Beans")
    ingredientList.push("Stout")
    ingredientList.push("Dark Chocolate")
    ingredientList.append("sometimes Caramelized Onions")
    println(ingredientList)

    println("""Together they make my "Everything but the kitchen sink chili": """)

    val fullList = LinkedList<String>()

    fullList.connectLists(spiceList, ingredientList, fullList)
    println(fullList)

    fullList.append("Portabella Mushrooms as soon as the ale starts boiling down")
    println("For the more adventurous: ")
    println(fullList)

    println("Penultimate: ${fullList.tail?.previous?.value}")

    println("For the southwest version: ")

    fullList.remark(4, "Black olives")
    fullList.remark(8, "Corn")
    fullList.remark(9, "canned Chipotles in Adobo sauce")
    fullList.remark(17, "or sometimes a sprinkle of any cheese and fresh onions")
    println(fullList)

    println("Any combination tastes good with a light hand on the spices.")
    fullList.removeAtIndex(0)
    println(fullList)

    println("the middle is " + fullList.middleNode(fullList.head, fullList.tail).value)
    fullList.binarySearch()

}