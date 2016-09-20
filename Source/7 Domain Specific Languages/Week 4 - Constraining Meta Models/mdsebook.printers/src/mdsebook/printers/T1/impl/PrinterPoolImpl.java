/**
 */
package mdsebook.printers.T1.impl;

import mdsebook.printers.T1.Fax;
import mdsebook.printers.T1.Printer;
import mdsebook.printers.T1.PrinterPool;
import mdsebook.printers.T1.T1Package;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Printer Pool</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link mdsebook.printers.T1.impl.PrinterPoolImpl#getPrinter <em>Printer</em>}</li>
 *   <li>{@link mdsebook.printers.T1.impl.PrinterPoolImpl#getFax <em>Fax</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PrinterPoolImpl extends MinimalEObjectImpl.Container implements PrinterPool {
	/**
	 * The cached value of the '{@link #getPrinter() <em>Printer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrinter()
	 * @generated
	 * @ordered
	 */
	protected Printer printer;

	/**
	 * The cached value of the '{@link #getFax() <em>Fax</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFax()
	 * @generated
	 * @ordered
	 */
	protected Fax fax;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PrinterPoolImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return T1Package.Literals.PRINTER_POOL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Printer getPrinter() {
		return printer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPrinter(Printer newPrinter, NotificationChain msgs) {
		Printer oldPrinter = printer;
		printer = newPrinter;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, T1Package.PRINTER_POOL__PRINTER, oldPrinter, newPrinter);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrinter(Printer newPrinter) {
		if (newPrinter != printer) {
			NotificationChain msgs = null;
			if (printer != null)
				msgs = ((InternalEObject)printer).eInverseRemove(this, T1Package.PRINTER__POOL, Printer.class, msgs);
			if (newPrinter != null)
				msgs = ((InternalEObject)newPrinter).eInverseAdd(this, T1Package.PRINTER__POOL, Printer.class, msgs);
			msgs = basicSetPrinter(newPrinter, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, T1Package.PRINTER_POOL__PRINTER, newPrinter, newPrinter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Fax getFax() {
		return fax;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFax(Fax newFax, NotificationChain msgs) {
		Fax oldFax = fax;
		fax = newFax;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, T1Package.PRINTER_POOL__FAX, oldFax, newFax);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFax(Fax newFax) {
		if (newFax != fax) {
			NotificationChain msgs = null;
			if (fax != null)
				msgs = ((InternalEObject)fax).eInverseRemove(this, T1Package.FAX__POOL, Fax.class, msgs);
			if (newFax != null)
				msgs = ((InternalEObject)newFax).eInverseAdd(this, T1Package.FAX__POOL, Fax.class, msgs);
			msgs = basicSetFax(newFax, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, T1Package.PRINTER_POOL__FAX, newFax, newFax));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case T1Package.PRINTER_POOL__PRINTER:
				if (printer != null)
					msgs = ((InternalEObject)printer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - T1Package.PRINTER_POOL__PRINTER, null, msgs);
				return basicSetPrinter((Printer)otherEnd, msgs);
			case T1Package.PRINTER_POOL__FAX:
				if (fax != null)
					msgs = ((InternalEObject)fax).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - T1Package.PRINTER_POOL__FAX, null, msgs);
				return basicSetFax((Fax)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case T1Package.PRINTER_POOL__PRINTER:
				return basicSetPrinter(null, msgs);
			case T1Package.PRINTER_POOL__FAX:
				return basicSetFax(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case T1Package.PRINTER_POOL__PRINTER:
				return getPrinter();
			case T1Package.PRINTER_POOL__FAX:
				return getFax();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case T1Package.PRINTER_POOL__PRINTER:
				setPrinter((Printer)newValue);
				return;
			case T1Package.PRINTER_POOL__FAX:
				setFax((Fax)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case T1Package.PRINTER_POOL__PRINTER:
				setPrinter((Printer)null);
				return;
			case T1Package.PRINTER_POOL__FAX:
				setFax((Fax)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case T1Package.PRINTER_POOL__PRINTER:
				return printer != null;
			case T1Package.PRINTER_POOL__FAX:
				return fax != null;
		}
		return super.eIsSet(featureID);
	}

} //PrinterPoolImpl
