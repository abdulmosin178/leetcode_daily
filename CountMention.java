import java.util.*;

class Solution {
    public int[] countMentions(int numberOfUsers, List<List<String>> events) {
        int[] mentions = new int[numberOfUsers];
        boolean[] online = new boolean[numberOfUsers];
        Arrays.fill(online, true);

        // offlineUntil[i] = timestamp when user i becomes online again (exclusive)
        int[] offlineUntil = new int[numberOfUsers];
        Arrays.fill(offlineUntil, 0);

        // To ensure stable tie-breaking (process OFFLINE before MESSAGE at same timestamp),
        // attach original index and sort by (timestamp, typePriority)
        class Ev {
            List<String> e;
            int time;
            int idx;
            Ev(List<String> e, int idx) {
                this.e = e;
                this.time = Integer.parseInt(e.get(1));
                this.idx = idx;
            }
        }

        List<Ev> evs = new ArrayList<>();
        for (int i = 0; i < events.size(); i++) evs.add(new Ev(events.get(i), i));

        evs.sort((a, b) -> {
            if (a.time != b.time) return Integer.compare(a.time, b.time);
            String ta = a.e.get(0), tb = b.e.get(0);
            // OFFLINE should come before MESSAGE when timestamps equal
            if (!ta.equals(tb)) {
                if (ta.equals("OFFLINE")) return -1;
                if (tb.equals("OFFLINE")) return 1;
            }
            // keep original order for ties of same type
            return Integer.compare(a.idx, b.idx);
        });

        for (Ev evObj : evs) {
            List<String> e = evObj.e;
            int time = evObj.time;
            String type = e.get(0);

            // Reactivate any users whose offline period ended at or before 'time'
            for (int i = 0; i < numberOfUsers; i++) {
                if (!online[i] && offlineUntil[i] <= time) {
                    online[i] = true;
                }
            }

            if (type.equals("OFFLINE")) {
                int id = Integer.parseInt(e.get(2));
                // The problem guarantees the user is online when OFFLINE event occurs.
                online[id] = false;
                offlineUntil[id] = time + 60;
            } else { // MESSAGE
                String msg = e.get(2);
                if (msg.equals("ALL")) {
                    for (int i = 0; i < numberOfUsers; i++) mentions[i]++;
                } else if (msg.equals("HERE")) {
                    for (int i = 0; i < numberOfUsers; i++) {
                        if (online[i]) mentions[i]++;
                    }
                } else {
                    String[] parts = msg.split("\\s+");
                    for (String p : parts) {
                        if (p.startsWith("id")) {
                            int id = Integer.parseInt(p.substring(2));
                            mentions[id]++;
                        }
                    }
                }
            }
        }

        return mentions;
    }
}
