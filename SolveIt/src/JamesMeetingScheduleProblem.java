import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JamesMeetingScheduleProblem {
/**
 * Problem Statement
 * https://www.chegg.com/homework-help/questions-and-answers/james-businessman-tight-schedule-week-week-starts-monday-00-00-ends-sunday-24-00-schedule--q32456897
 * James is a businessman. He is on a tight schedule this week. The week starts on Monday at 00:00 and ends on Sunday at 24:00. His schedule consists of M meetings he needs to take part in. Each of them will take place in a period of time, beginning and ending on the same day (there are no two ongoing meetings at the same time). James is very tired, thus he needs to find the longest possible time slot to sleep. In other words, he wants to find the longest period of time when there are no ongoing meetings. The sleeping break can begin and end on different days and should begin and end in the same week. 

You are given a string containing M lines. Each line is a substring representing one meeting in the schedule, in the format "Ddd hh:mm-hh:mm". "Ddd" is a three-letter abbreviation for the day of the week when the meeting takes place: "Mon" (Monday), "Tue", "Wed", "Thu", "Fri", "Sat", "Sun". "hh:mm-hh:mm" means the beginning time and the ending time of the meeting (from 00:00 to 24:00 inclusive). 

The given times represent exact moments of time. So, there are exactly five minutes between 13:40 and 13:45. 

For example, given a string: 
"Sun 10:00-20:00 
Fri 05:00-10:00 
Fri 16:30-23:50 
Sat 10:00-24:00 
Sun 01:00-04:00 
Sat 02:00-06:00 
Tue 03:30-18:15 
Tue 19:00-20:00 
Wed 04:25-15:14 
Wed 15:14-22:40 
Thu 00:00-23:59 
Mon 05:00-13:00 
Mon 15:00-21:00" 

the longest time slot when James can sleep is 505 minutes, since James can sleep from Tuesday 20:00 to Wednesday 04:25, which gives 8 hours and 25 minutes = 505 minutes. 

Also, for a string: 

"Mon 01:00-23:00 
Tue 01:00-23:00 
Wed 01:00-23:00 
Thu 01:00-23:00 
Fri 01:00-23:00 
Sat 01:00-23:00 
Sun 01:00-21:00" 

James should sleep on Sunday from 21:00 to 24:00 (180 minutes). 

Write a function:


class Solution { 
        public int solution(String S); 
 }
that, given a string S representing the schedule, returns the length of the longest time slot when James can sleep (in minutes). 

Assume that: 
M is an integer within the range [1..100]; 
Each line of the input string is in the format "Ddd hh:mm-hh:mm" and lines are separated with newline characters; 
There cannot be two ongoing meetings at any time; 
Each meeting lasts at least 1 minute. 

In your solution, focus on correctness. The performance of your solution will not be the focus of the assessment.
 * 
 * 
 */

	public static int solution(String N) {
		List<String> inputStringList = Arrays.asList(N.split("\n"));
		Map<String, Integer> daysMap = new HashMap<>();
		daysMap.put("MON", 0);
		daysMap.put("TUE", 1);
		daysMap.put("WED", 2);
		daysMap.put("THU", 3);
		daysMap.put("FRI", 4);
		daysMap.put("SAT", 5);
		daysMap.put("SUN", 6);
		String totalMin = "0-".concat(String.valueOf(7*24*60));
		List<String> scheduleList = new ArrayList<>();
		scheduleList.add(totalMin);
		for(String schedule : inputStringList) {
			String day = schedule.substring(0,3).toUpperCase();
			Integer startHrs = Integer.valueOf(schedule.substring(4,6));
			Integer startMin = Integer.valueOf(schedule.substring(7,9));
			Integer startMeetingMins = daysMap.get(day) * 24 *60 + startHrs *60 + startMin;
			Integer endHrs = Integer.valueOf(schedule.substring(10,12));
			Integer endMin = Integer.valueOf(schedule.substring(13));
			Integer endMeetingMin = daysMap.get(day) * 24 * 60 + endHrs *60 + endMin;
			List<String> tempList = new ArrayList<>();
			for(String availableSlot : scheduleList) {
				String[] availableTime = availableSlot.split("-");
				Integer start = Integer.valueOf(availableTime[0]);
				Integer end = Integer.valueOf(availableTime[1]);
				if(startMeetingMins >= start && startMeetingMins < end && endMeetingMin > start && endMeetingMin <= end) {
					if(startMeetingMins != start && endMeetingMin != end) {
						String newStart = String.valueOf(start);
						String newEnd = String.valueOf(startMeetingMins);
						tempList.add(newStart.concat("-").concat(newEnd));
						String newStart1 = String.valueOf(endMeetingMin);
						String newEnd1 = String.valueOf(end);
						tempList.add(newStart1.concat("-").concat(newEnd1));
					}else if((startMeetingMins == start && endMeetingMin != end) || (startMeetingMins != start && endMeetingMin == end)) {
						String newStart = startMeetingMins == start ? String.valueOf(endMeetingMin) : String.valueOf(start);
						String newEnd = endMeetingMin == end ? String.valueOf(startMeetingMins) : String.valueOf(end);
						tempList.add(newStart.concat("-").concat(newEnd));
					}
				}else {
					tempList.add(availableSlot);
				}
			}
			scheduleList.clear();
			scheduleList.addAll(tempList);
		}
		Integer result = 0;
		for(String availableSlot : scheduleList) {
			String[] splitTimeInterval = availableSlot.split("-");
			Integer difference = Integer.valueOf(splitTimeInterval[1]) - Integer.valueOf(splitTimeInterval[0]);		
			//System.out.println(availableSlot + " ==>" + difference);
			result = result > difference ? result : difference;
		}
		return result;
	}

	public static void main(String[] args) {
		//TestCase-1
		int result1 = JamesMeetingScheduleProblem.solution("Sun 10:00-20:00\nFri 05:00-10:00\nFri 16:30-23:50\nSat 10:00-24:00\nSun 01:00-04:00\nSat 02:00-06:00\nTue 03:30-18:15\nTue 19:00-20:00\nWed 04:25-15:14\nWed 15:14-22:40\nThu 00:00-23:59\nMon 05:00-13:00\nMon 15:00-21:00"); 
		System.out.println("result1 ==>"+result1);

		//TestCase-1
		int result2 = JamesMeetingScheduleProblem.solution("Mon 01:00-23:00\nTue 01:00-23:00\nWed 01:00-23:00\nThu 01:00-23:00\nFri 01:00-23:00\nSat 01:00-23:00\nSun 01:00-21:00");
		System.out.println("result2 ==>"+result2);
	}
}
