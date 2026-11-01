package edu.ucf.knightcompass;

final class Event {
    final String title, date, time, building, room, organizer, contact, about;
    final boolean virtual;
    Event(String title, String date, String time, String building, String room, String organizer, String contact, String about, boolean virtual) {
        this.title=title; this.date=date; this.time=time; this.building=building; this.room=room; this.organizer=organizer; this.contact=contact; this.about=about; this.virtual=virtual;
    }
    String address() { return "4000 Central Florida Blvd, Orlando, FL"; }
    String mapQuery() { return "UCF "+building+", "+address(); }
    String parking() {
        if(building.equals("Student Union")) return "Visitor parking is available in Garage H (Lot H1), adjacent to the Student Union. Rates: $2/hr, max $8/day. Pay at the kiosk or via the ParkMobile app — Zone 2441.";
        return "Parking details for this location haven't been added yet. Contact the organizer at "+contact+" for visitor parking information.";
    }
    static final Event[] ALL = {
        new Event("Spring Research Symposium", "Friday, April 11, 2025", "10:00 AM – 4:00 PM", "Student Union", "Pegasus Ballroom A", "College of Sciences", "research@ucf.edu", "Join faculty, graduate students, and undergraduate researchers for an afternoon of presentations, posters, and panels showcasing the latest discoveries across all disciplines. Awards will be given in seven categories. Light refreshments provided.", false),
        new Event("Career Fair – Engineering & Tech", "Wednesday, April 16, 2025", "11:00 AM – 3:00 PM", "Recreation & Wellness Center", "Main Court", "Career Services", "career@ucf.edu", "Meet employers, explore internships, and connect with professionals in engineering and technology. Bring your resume and your questions.", false),
        new Event("First Generation Career Connections", "Friday, April 18, 2025", "12:00 PM – 2:00 PM", "Student Union", "Room 218", "First Generation Student Success", "firstgen@ucf.edu", "Connect with fellow first-generation Knights and learn about career resources, networking, and opportunities on campus.", false),
        new Event("Knights After Dark: Trivia Night", "Friday, April 18, 2025", "8:00 PM – 11:00 PM", "Memory Mall", "Outdoor lawn", "Student Government", "sg@ucf.edu", "Bring your friends for an evening of trivia, friendly competition, and campus community. All Knights are welcome.", false),
        new Event("Virtual Study Bootcamp", "Saturday, April 19, 2025", "9:00 AM – 1:00 PM", "Online", "Virtual meeting", "Academic Success Center", "success@ucf.edu", "Get ready for finals with guided study sessions, practical study strategies, and support from fellow Knights.", true),
        new Event("Campus Sustainability Fair", "Sunday, April 20, 2025", "12:00 PM – 5:00 PM", "Reflecting Pond", "Outdoor plaza", "Green UCF", "green@ucf.edu", "Discover simple ways to make campus greener. Explore student projects, sustainability activities, and community resources.", false)
    };
}
