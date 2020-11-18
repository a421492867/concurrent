
# 线程池
### newFixedThreadPool 固定长度的线程池
### newCachedThreadPool 缓存线程池
### newSingleThreadExecutor 单线程Executor 创建单个工作者线程
### newScheduledThreadPool 固定长度线程池 延时或定时执行任务 类似Timer  
     Timer基于绝对时间 而ScheduledThreadPool基于相对时间
     Timer只会创建一个线程
     TimerTask抛出一个未检查的异常会直接停止  线程泄漏