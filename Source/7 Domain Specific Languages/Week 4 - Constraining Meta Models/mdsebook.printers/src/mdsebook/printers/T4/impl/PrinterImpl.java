/**
 */
package mdsebook.printers.T4.impl;

import mdsebook.printers.T4.ColorPrinterHead;
import mdsebook.printers.T4.Printer;
import mdsebook.printers.T4.PrinterPool;
import mdsebook.printers.T4.T4Package;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Printer</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link mdsebook.printers.T4.impl.PrinterImpl#getPool <em>Pool</em>}</li>
 *   <li>{@link mdsebook.printers.T4.impl.PrinterImpl#getHead <em>Head</em>}</li>
 *   <li>{@link mdsebook.printers.T4.impl.PrinterImpl#isColor <em>Color</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PrinterImpl extends MinimalEObjectImpl.Container implements Printer {
	/**
	 * The cached value of the '{@link #getHead() <em>Head</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHead()
	 * @generated
	 * @ordered
	 */
	protected ColorPrinterHead head;

	/**
	 * The default value of the '{@link #isColor() <em>Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isColor()
	 * @generated
	 * @ordered
	 */
	protected static final boolean COLOR_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isColor() <em>Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isColor()
	 * @generated
	 * @ordered
	 */
	protected boolean color = COLOR_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PrinterImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return T4Package.Literals.PRINTER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrinterPool getPool() {
		if (eContainerFeatureID() != T4Package.PRINTER__POOL) return null;
		return (PrinterPool)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPool(PrinterPool newPool, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newPool, T4Package.PRINTER__POOL, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPool(PrinterPool newPool) {
		if (newPool != eInternalContainer() || (eContainerFeatureID() != T4Package.PRINTER__POOL && newPool != null)) {
			if (EcoreUtil.isAncestor(this, newPool))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newPool != null)
				msgs = ((InternalEObject)newPool).eInverseAdd(this, T4Package.PRINTER_POOL__PRINTER, PrinterPool.class, msgs);
			msgs = basicSetPool(newPool, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, T4Package.PRINTER__POOL, newPool, newPool));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ColorPrinterHead getHead() {
		return head;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHead(ColorPrinterHead newHead, NotificationChain msgs) {
		ColorPrinterHead oldHead = head;
		head = newHead;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, T4Package.PRINTER__HEAD, oldHead, newHead);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHead(ColorPrinterHead newHead) {
		if (newHead != head) {
			NotificationChain msgs = null;
			if (head != null)
				msgs = ((InternalEObject)head).eInverseRemove(this, T4Package.COLOR_PRINTER_HEAD__PRINTER, ColorPrinterHead.class, msgs);
			if (newHead != null)
				msgs = ((InternalEObject)newHead).eInverseAdd(this, T4Package.COLOR_PRINTER_HEAD__PRINTER, ColorPrinterHead.class, msgs);
			msgs = basicSetHead(newHead, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, T4Package.PRINTER__HEAD, newHead, newHead));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isColor() {
		return color;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setColor(boolean newColor) {
		boolean oldColor = color;
		color = newColor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, T4Package.PRINTER__COLOR, oldColor, color));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case T4Package.PRINTER__POOL:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetPool((PrinterPool)otherEnd, msgs);
			case T4Package.PRINTER__HEAD:
				if (head != null)
					msgs = ((InternalEObject)head).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - T4Package.PRINTER__HEAD, null, msgs);
				return basicSetHead((ColorPrinterHead)otherEnd, msgs);
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
			case T4Package.PRINTER__POOL:
				return basicSetPool(null, msgs);
			case T4Package.PRINTER__HEAD:
				return basicSetHead(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case T4Package.PRINTER__POOL:
				return eInternalContainer().eInverseRemove(this, T4Package.PRINTER_POOL__PRINTER, PrinterPool.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case T4Package.PRINTER__POOL:
				return getPool();
			case T4Package.PRINTER__HEAD:
				return getHead();
			case T4Package.PRINTER__COLOR:
				return isColor();
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
			case T4Package.PRINTER__POOL:
				setPool((PrinterPool)newValue);
				return;
			case T4Package.PRINTER__HEAD:
				setHead((ColorPrinterHead)newValue);
				return;
			case T4Package.PRINTER__COLOR:
				setColor((Boolean)newValue);
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
			case T4Package.PRINTER__POOL:
				setPool((PrinterPool)null);
				return;
			case T4Package.PRINTER__HEAD:
				setHead((ColorPrinterHead)null);
				return;
			case T4Package.PRINTER__COLOR:
				setColor(COLOR_EDEFAULT);
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
			case T4Package.PRINTER__POOL:
				return getPool() != null;
			case T4Package.PRINTER__HEAD:
				return head != null;
			case T4Package.PRINTER__COLOR:
				return color != COLOR_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (color: ");
		result.append(color);
		result.append(')');
		return result.toString();
	}

} //PrinterImpl
