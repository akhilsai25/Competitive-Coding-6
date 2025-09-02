// Using Linkedhashmap to store the string and its timestamp. At runtime if any of the string is expired i.e., more than 10 s in delay we can delete it. Otherwise we can add them
class Logger {

    Map<String, Integer> map;
    private final Object object = new Object();
    public Logger() {
        map = new LinkedHashMap();
    }
    
    public boolean shouldPrintMessage(int timestamp, String message) {
        synchronized(object) {
            Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Integer> entry = it.next();

                if (entry.getValue() + 10 < timestamp) {
                    it.remove();
                }
            }
            if(!map.containsKey(message)) {
            synchronized(object) {
                map.put(message, timestamp);
            }
            return true;
        } else {
            int existingTimeStamp = map.get(message);
            if(timestamp-existingTimeStamp<10) {
                return false;
            } else {
                map.put(message, timestamp);
                return true;
            }
        }
        }
    }
}

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */
