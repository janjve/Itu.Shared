/**
 */
package mdsebook.printers.T2.impl;

import mdsebook.printers.T2.*;

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
public class T2FactoryImpl extends EFactoryImpl implements T2Factory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static T2Factory init() {
		try {
			T2Factory theT2Factory = (T2Factory)EPackage.Registry.INSTANCE.getEFactory(T2Package.eNS_URI);
			if (theT2Factory != null) {
				return theT2Factory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new T2FactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public T2FactoryImpl() {
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
			case T2Package.PRINTER_POOL: return createPrinterPool();
			case T2Package.COPIER: return createCopier();
			case T2Package.SCANNER: return createScanner();
			case T2Package.FAX: return createFax();
			case T2Package.PRINTER: return createPrinter();
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
	public Copier createCopier() {
		CopierImpl copier = new CopierImpl();
		return copier;
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
	public Fax createFax() {
		FaxImpl fax = new FaxImpl();
		return fax;
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
	public T2Package getT2Package() {
		return (T2Package)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static T2Package getPackage() {
		return T2Package.eINSTANCE;
	}

} //T2FactoryImpl
