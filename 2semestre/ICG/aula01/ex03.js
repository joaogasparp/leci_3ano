import * as THREE from 'three';
import { OrbitControls } from 'three/orbitcontrols';

function createTree() {
    const tree = new THREE.Group();

    // Tronco
    const trunkGeometry = new THREE.CylinderGeometry(0.5, 1, 5, 8);
    const trunkMaterial = new THREE.MeshPhongMaterial({ color: 0x8B4513 }); // Marrom
    const trunk = new THREE.Mesh(trunkGeometry, trunkMaterial);
    tree.add(trunk);

    // Folhas
    const leavesGeometry = new THREE.SphereGeometry(1, 8, 6);
    const leavesMaterial = new THREE.MeshPhongMaterial({ color: 0x00FF00 }); // Verde
    const leavesMesh = new THREE.InstancedMesh(leavesGeometry, leavesMaterial, 500); // Criando instâncias

    const leavesOffset = new THREE.Vector3();
    const leavesMatrix = new THREE.Matrix4();

    for (let i = 0; i < 70; i++) {
        // Posicionando as instâncias de folhas de forma aleatória
        leavesOffset.set(Math.random() * 4 - 2, Math.random() * 2 + 3, Math.random() * 4 - 2);
        leavesMatrix.setPosition(leavesOffset);
        leavesMesh.setMatrixAt(i, leavesMatrix);
    }

    tree.add(leavesMesh);

    return tree;
}

function init() {
    const scene = new THREE.Scene();

    const camera = new THREE.PerspectiveCamera(70, window.innerWidth / window.innerHeight, 1, 1000);
    camera.position.z = 15;
    camera.lookAt(scene.position);
    scene.add(camera);

    const renderer = new THREE.WebGLRenderer({ antialias: true });
    renderer.setClearColor(new THREE.Color(0x87CEEB)); // Céu azul claro
    renderer.setPixelRatio(window.devicePixelRatio);
    renderer.setSize(window.innerWidth, window.innerHeight);
    document.body.appendChild(renderer.domElement);

    const controls = new OrbitControls(camera, renderer.domElement);
    controls.enableDamping = true;
    controls.dampingFactor = 0.25;
    controls.screenSpacePanning = false;
    controls.maxPolarAngle = Math.PI / 2;

    // Adicionando luz ambiente
    const ambientLight = new THREE.AmbientLight(0xffffff, 0.2);
    scene.add(ambientLight);

    // Adicionando luz direcional
    const directionalLight = new THREE.DirectionalLight(0xffffff, 1);
    directionalLight.position.set(-1, 1, 1);
    scene.add(directionalLight);

    // Criando a árvore e posicionando no cenário
    const tree = createTree();
    scene.add(tree);

    renderer.render(scene, camera);

    function animate() {
        requestAnimationFrame(animate);
        controls.update();
        renderer.render(scene, camera);
    }

    animate();
}

window.onload = init;
