/**
 */
package mdsebook.printers.T6.impl;

import mdsebook.printers.T6.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class T6FactoryImpl extends EFactoryImpl implements T6Factory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static T6Factory init() {
		try {
			T6Factory theT6Factory = (T6Factory)EPackage.Registry.INSTANCE.getEFactory(T6Package.eNS_URI);
			if (theT6Factory != null) {
				return theT6Factory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new T6FactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public T6FactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case T6Package.PRINTER_POOL: return createPrinterPool();
			case T6Package.PRINTER: return createPrinter();
			case T6Package.SCANNER: return createScanner();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrinterPool createPrinterPool() {
		PrinterPoolImpl printerPool = new PrinterPoolImpl();
		return printerPool;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Printer createPrinter() {
		PrinterImpl printer = new PrinterImpl();
		return printer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Scanner createScanner() {
		ScannerImpl scanner = new ScannerImpl();
		return scanner;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public T6Package getT6Package() {
		return (T6Package)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static T6Package getPackage() {
		return T6Package.eINSTANCE;
	}

} //T6FactoryImpl
