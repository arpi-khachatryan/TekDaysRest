package tekdays


class TekUser {

    String fullName
    String userName
    String password
    String email

    static constraints = {
        fullName size: 2..10
        userName()
        password blank: false
        email unique: true
    }

    String toString() { fullName }
}
