/**
 */
package mdsebook.printers.T4.impl;

import mdsebook.printers.T4.*;

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
public class T4FactoryImpl extends EFactoryImpl implements T4Factory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static T4Factory init() {
		try {
			T4Factory theT4Factory = (T4Factory)EPackage.Registry.INSTANCE.getEFactory(T4Package.eNS_URI);
			if (theT4Factory != null) {
				return theT4Factory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new T4FactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public T4FactoryImpl() {
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
			case T4Package.PRINTER_POOL: return createPrinterPool();
			case T4Package.PRINTER: return createPrinter();
			case T4Package.COLOR_PRINTER_HEAD: return createColorPrinterHead();
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
	public ColorPrinterHead createColorPrinterHead() {
		ColorPrinterHeadImpl colorPrinterHead = new ColorPrinterHeadImpl();
		return colorPrinterHead;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public T4Package getT4Package() {
		return (T4Package)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static T4Package getPackage() {
		return T4Package.eINSTANCE;
	}

} //T4FactoryImpl
