### 同步容器
    Vector HashTable以及由Collections.synchronizedXXX创建的
    
    线程安全  但是复合操作需要额外的加锁
    
### 并发容器
    ConcurrentHashMap、 CopyOnWriteList等
    并发容器是针对多个线程并发访问设计的