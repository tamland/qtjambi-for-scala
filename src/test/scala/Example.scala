import com.trolltech.qt.gui.QApplication
import com.trolltech.qt.gui.QPushButton

object Example {

  def main(args: Array[String]): Unit = {
    QApplication.initialize(args)

    val btn = new QPushButton("Example");
    
    btn.released.connect(release _)
    btn.clicked.connect( (arg:java.lang.Boolean) => println("clicked") )
    
    btn.resize(200, 100);
    btn.show();

    QApplication.exec();
  }
  
  def release(): Unit = {
    println("released")
  }

}
