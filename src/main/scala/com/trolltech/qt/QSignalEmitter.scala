package com.trolltech.qt

import com.trolltech.qt.core.Qt
import com.trolltech.qt.internal.QSignalEmitterInternal
import com.trolltech.qt.core.QCoreApplication
import com.trolltech.qt.core.QObject
import scala.actors.Actor
import scala.actors.Actor._
import scala.collection.mutable.HashSet


class QSignalEmitter extends QSignalEmitterInternal {

  override def thread(): Thread = {
    return Thread.currentThread();
  }

  override def signalsBlocked(): Boolean = {
    return false;
  }

  def blockSignals(b: Boolean): Boolean = {
    return false
  }

  def disconnect() = {

  }

  
  trait DepricatedAPI extends super.AbstractSignalInternal {
    @deprecated
    override def connect(receiver: Object, method: String): Unit = {
      super.connect(receiver, method)
    }
    @deprecated
    override def connect(receiver: Object, method: String, ctype: Qt.ConnectionType): Unit = {
      super.connect(receiver, method, ctype)
    }
    @deprecated
    override def connect(signalOut: QSignalEmitterInternal#AbstractSignalInternal): Unit = {
      super.connect(signalOut)
    }
    @deprecated
    override def connect(signalOut: QSignalEmitterInternal#AbstractSignalInternal, ctype: Qt.ConnectionType): Unit = {
      super.connect(signalOut, ctype)
    }
    @deprecated
    override def disconnect(receiver: AnyRef, method: String): Boolean = {
      super.disconnect(receiver, method)
    }
    @deprecated
    override def disconnect(receiver: AnyRef): Boolean = {
      super.disconnect(receiver)
    }
    @deprecated
    def disconnect(): Boolean = {
      super.disconnect(null, null)
    }
    @deprecated
    def disconnect(signalOut: QSignalEmitterInternal#AbstractSignalInternal): Boolean = {
      super.removeConnection(signalOut, com.trolltech.qt.internal.QtJambiInternal.findEmitMethod(signalOut));
    }
  }
  
  class AbstractSignal[T] extends super.AbstractSignalInternal with DepricatedAPI {
    
    private var slots = new HashSet[T => Any]()
    private var initialized = false
    
    def connect(f: T => Any): T => Any = {
      if (!initialized) {
    	  initialized = true
    	  initializeSignal()
      }
      slots += f
      f
    }
    
    def connect(signalOut: QSignalEmitter#AbstractSignal[T]): T => Any = {
      connect(signalOut.emit _)
    }
    
    def disconnect(f: T => Any): Boolean = {
      val ret = slots.contains(f)
      slots.remove(f)
      ret
    }
    
    protected[AbstractSignal] def emit(arg:T) = {
      slots.foreach { f =>
        dispatcher ! Next(f, arg)
      }

      var vararg = arg match {
        case arg:Product => arg.productIterator.map(_.asInstanceOf[AnyRef]).toSeq //tuple
        case arg:AnyRef => Seq[AnyRef](arg) //single argument
        case _ => Seq[AnyRef]() //zero argument
      }
      emit_helper(vararg: _*)
    }
    private case class Next(f: T => Any, arg: T)
    
    private val dispatcher: Actor = actor {
	  loop {
	    react {
	      case Next(f, arg) => {
	        f(arg)
	      }
	    }
	  }
    }
  }

  class Signal0 extends AbstractSignal[(Unit)] {
   def emit() = {
      super.emit()
   }
   def connect(f: () => Any) = {
     super.connect( (Unit) => f() )
   }
  }
  
  class Signal1[A] extends AbstractSignal[(A)] {
    def emit(arg:A) = {
      super.emit(arg)
    }
  }
  
  class Signal2[A,B] extends AbstractSignal[(A,B)] {
    def connect(f: (A,B) => Any): Unit = {
      super.connect(Function.tupled(f))
    }
    def emit(arg1:A, arg2:B) = {
      super.emit((arg1, arg2))
    }
  }
  
  class Signal3[A,B,C] extends AbstractSignal[(A,B,C)] {
    def connect(f: (A,B,C) => Any): Unit = {
      super.connect(Function.tupled(f))
    }
    def emit(arg1:A, arg2:B, arg3:C) = {
      super.emit((arg1, arg2, arg3))
    }
  }
  
  class Signal4[A,B,C,D] extends AbstractSignal[(A,B,C,D)] {
    def connect(f: (A,B,C,D) => Any): Unit = {
      super.connect(Function.tupled(f))
    }
    def emit(arg1:A, arg2:B, arg3:C, arg4:D) = {
      super.emit((arg1, arg2, arg3, arg4))
    }
  }
  
  class Signal5[A,B,C,D,E] extends AbstractSignal[(A,B,C,D,E)] {
    def connect(f: (A,B,C,D,E) => Any): Unit = {
      super.connect(Function.tupled(f))
    }
    def emit(arg1:A, arg2:B, arg3:C, arg4:D, arg5:E) = {
      super.emit((arg1, arg2, arg3, arg4, arg5))
    }
  }
  
  class PrivateSignal0 extends AbstractSignal[(Unit)] {
   def emit() = {
      super.emit()
   }
   def connect(f: () => Any) = {
     super.connect( (Unit) => f() )
   }
  }
  
  class PrivateSignal1[A] extends AbstractSignal[(A)] {
    private  def emit(arg:A) = {
      super.emit(arg)
    }
  }
  
  class PrivateSignal2[A,B] extends AbstractSignal[(A,B)] {
    def connect(f: (A,B) => Any): Unit = {
      super.connect(Function.tupled(f))
    }
    private def emit(arg1:A, arg2:B) = {
      super.emit((arg1, arg2))
    }
  }
  
  class PrivateSignal3[A,B,C] extends AbstractSignal[(A,B,C)] {
    def connect(f: (A,B,C) => Any): Unit = {
      super.connect(Function.tupled(f))
    }
    private def emit(arg1:A, arg2:B, arg3:C) = {
      super.emit((arg1, arg2, arg3))
    }
  }
  
  class PrivateSignal4[A,B,C,D] extends AbstractSignal[(A,B,C,D)] {
    def connect(f: (A,B,C,D) => Any): Unit = {
      super.connect(Function.tupled(f))
    }
    private def emit(arg1:A, arg2:B, arg3:C, arg4:D) = {
      super.emit((arg1, arg2, arg3, arg4))
    }
  }
  
  class PrivateSignal5[A,B,C,D,E] extends AbstractSignal[(A,B,C,D,E)] {
    def connect(f: (A,B,C,D,E) => Any): Unit = {
      super.connect(Function.tupled(f))
    }
    private def emit(arg1:A, arg2:B, arg3:C, arg4:D, arg5:E) = {
      super.emit((arg1, arg2, arg3, arg4, arg5))
    }
  }

}

