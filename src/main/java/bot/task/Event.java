package bot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A special type of Task characterised by the input "/at" which indicates the date and time of which the task
 * should start.
 */
public class Event extends Task {
    private LocalDateTime timePeriod;

    public Event(String name, String timePeriod) {
        super(name);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.timePeriod = LocalDateTime.parse(timePeriod, formatter);
    }

    public Event(String name, String timePeriod, boolean done) {
        super(name, done);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.timePeriod = LocalDateTime.parse(timePeriod, formatter);
    }

    /**
     * Serialises the object.
     * @return A string that is formatted to be read and stored in Storage.
     */
    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "E" + " | " + super.toFileFormat() + " | " + this.timePeriod.format(formatter)
                + "\n";
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
                timePeriod.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")));
    }
}