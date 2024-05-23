# Banking-Simulator-with-Synchronized-Threads
An Application Employing Synchronized/Cooperating Multiple Threads In Java Using Locks – A Banking Simulator. Developed a sophisticated Java application simulating a banking system with synchronized and cooperating multiple threads. 

The application featured:

● Thread Management: Utilized a FixedThreadPool and an Executor to manage five depositor agents, ten withdrawal agents, and two auditor agents executing concurrently.

● Synchronization: Implemented synchronization to ensure mutual exclusion and to handle conditional waits for withdrawals when funds were insufficient, ensuring thread-safe access to the shared bank account object.

● Transaction Processing: Depositor agents made random deposits between $1 to $500, while withdrawal agents processed random withdrawals between $1 to $99. Auditor agents periodically verified account balances.

● Flagged Transactions: Integrated a mechanism to flag and log transactions exceeding specified thresholds ($350 for deposits and $75 for withdrawals) in a separate transaction log file (transactions.csv).

● Thread Coordination: Employed reentrant locks from the java.util.concurrent.locks package for implementing locking protocols without fairness policy, avoiding monopolization by any agent type.

● Real-time Simulation: Ensured realistic operation through thread sleeping and yielding, preventing back-to-back executions of withdrawal agents, and maintaining balanced thread execution.

● Output and Logging: The simulation produced detailed console output showing each transaction, its impact on the account balance, and flagged transactions. Included output logging to a file capturing at least 100 transactions for auditing purposes.

Skills: Java · JavaScript · Multithreading · Data Synchronization · File I/O Operations · Object-Oriented Design · Software Development Lifecycle · Data Management
