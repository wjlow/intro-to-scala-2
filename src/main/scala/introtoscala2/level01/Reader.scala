package introtoscala2.level01

case class Reader[R, A](run: R => A) {

  def map[B](f: A => B): Reader[R, B] =
    Reader((r: R) => f(run(r)))

  def flatMap[B](f: A => Reader[R, B]): Reader[R, B] =
    Reader((r: R) => f(run(r)).run(r))

}
