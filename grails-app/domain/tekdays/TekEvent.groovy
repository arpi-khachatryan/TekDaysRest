package tekdays


class TekEvent {

    String name

    static hasMany = [users: TekUser]

    static constraints = {
        name()
    }

    String toString() {
        "$name"
    }
}
