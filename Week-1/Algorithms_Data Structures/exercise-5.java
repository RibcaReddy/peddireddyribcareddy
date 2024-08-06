public class TaskManagementSystem {
    private class Node {
        Task task;
        Node next;

        public Node(Task task) {
            this.task = task;
        }
    }

    private Node head;

    public void addTask(Task task) {
        Node newNode = new Node(task);
        newNode.next = head;
        head = newNode;
        System.out.println("Task added: " + task);
    }

    public Task searchTask(int taskId) {
        Node current = head;
        while (current != null) {
            if (current.task.getTaskId() == taskId) {
                return current.task;
            }
            current = current.next;
        }
        return null;
    }

    public void deleteTask(int taskId) {
        Node current = head;
        Node previous = null;
        while (current != null && current.task.getTaskId() != taskId) {
            previous = current;
            current = current.next;
        }
        if (current != null) {
            if (previous == null) {
                head = current.next;
            } else {
                previous.next = current.next;
            }
            System.out.println("Task removed: " + current.task);
        } else {
            System.out.println("Task with ID " + taskId + " not found.");
        }
    }

    public void traverseTasks() {
        Node current = head;
        System.out.println("Task List:");
        while (current != null) {
            System.out.println(current.task);
            current = current.next;
        }
    }

    public static void main(String[] args) {
        TaskManagementSystem tms = new TaskManagementSystem();

        tms.addTask(new Task(1, "Design Interface", "Pending"));
        tms.addTask(new Task(2, "Implement Backend", "In Progress"));
        tms.addTask(new Task(3, "Test Application", "Pending"));

        tms.traverseTasks();

        Task task = tms.searchTask(2);
        System.out.println("Searched Task: " + task);

        tms.deleteTask(2);

        tms.traverseTasks();
    }
}
class Task {
    private int taskId;
    private String taskName;
    private String status;

    public Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getStatus() {
        return status;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
